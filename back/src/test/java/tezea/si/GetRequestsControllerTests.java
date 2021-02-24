package tezea.si;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import tezea.si.dao.SmallClientDAO;
import tezea.si.dao.SmallRequestDAO;
import tezea.si.dao.UserTezeaDAO;
import tezea.si.model.business.Site;
import tezea.si.model.business.SmallClient;
import tezea.si.model.business.UserTezea;
import tezea.si.model.business.request.RequestStatus;
import tezea.si.model.business.request.Service;
import tezea.si.model.business.request.SmallRequest;
import tezea.si.utils.TestUtils;

@SpringBootTest
@AutoConfigureMockMvc
public class GetRequestsControllerTests {

	private static final String REQUESTS_URL = "/requests";

	@Autowired
	UserTezeaDAO userDao;

	@Autowired
	SmallRequestDAO requestDao;

	@Autowired
	SmallClientDAO clientDao;

	@Autowired
	ObjectMapper mapper;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void before() {
		UserTezea user = new UserTezea();
		user.setUsername("jean");
		user.setPassword("$2a$10$sDP3s/p0M1TCOW6FizwLWulsnwT2BryFkLHqKusRRljaYKYOWVE7u");

		userDao.save(user);
	}

	@AfterEach
	public void destroy() {
		userDao.deleteAll();
		requestDao.deleteAll();
	}

	@Test
	public void cannotAccessWithoutToken() throws Exception {
		// Arrange
		String input = "{}";

		// Act, assert
		this.mockMvc.perform(
				get(REQUESTS_URL).contentType(MediaType.APPLICATION_JSON).content(input))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void returnsEmptyListIfNoRequests() throws Exception {
		// Arrange
		String input = "{}";
		String expected = "[]";

		// Act
		String result = this.mockMvc
				.perform(get(REQUESTS_URL).contentType(MediaType.APPLICATION_JSON)
						.content(input)
						.headers(TestUtils.userAuthorizationHeader(mockMvc)))
				.andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString(StandardCharsets.UTF_8);

		// Assert
		assertThat(result).isEqualTo(expected);
	}

	@Test
	public void returnsRequestsWithoutSearch() throws Exception {
		// Arrange
		SmallRequest request = new SmallRequest();
		request.setPhotos(List.of());
		SmallRequest request2 = new SmallRequest();
		request2.setPhotos(List.of());
		List<SmallRequest> expectedList = List.of(requestDao.save(request),
				requestDao.save(request2));
		String input = "{}";

		// Act
		String result = this.mockMvc
				.perform(get(REQUESTS_URL).contentType(MediaType.APPLICATION_JSON)
						.content(input)
						.headers(TestUtils.userAuthorizationHeader(mockMvc)))
				.andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString(StandardCharsets.UTF_8);

		// Assert
		List<SmallRequest> resultList = mapper.readValue(result,
				new TypeReference<List<SmallRequest>>() {
				});
		assertThat(resultList).usingRecursiveFieldByFieldElementComparator()
				.isEqualTo(expectedList);
	}

	@Test
	public void searchByIdReturns404IfNotFound() throws Exception {
		// Arrange
		String url = REQUESTS_URL + "/85";

		// Act, assert
		this.mockMvc.perform(get(url).headers(TestUtils.userAuthorizationHeader(mockMvc)))
				.andExpect(status().isNotFound());
	}

	@Test
	public void searchByIdReturnsRequest() throws Exception {
		// Arrange
		SmallRequest request = new SmallRequest();
		request.setPhotos(List.of());
		request = requestDao.save(request);
		String url = REQUESTS_URL + "/" + request.getId();

		// Act
		String response = this.mockMvc
				.perform(get(url).headers(TestUtils.userAuthorizationHeader(mockMvc)))
				.andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString(StandardCharsets.UTF_8);

		// Assert
		SmallRequest result = mapper.readValue(response, SmallRequest.class);
		assertThat(result).usingRecursiveComparison().isEqualTo(request);
		assertThat(result.getId()).isNotZero();
	}

	@Test
	public void returnsRequestsWithSearchByDescription() throws Exception {
		// Arrange
		String input = TestUtils.createJsonString("description", "be");

		SmallRequest request = new SmallRequest();
		request.setDescription("as if");
		request.setPhotos(List.of());
		requestDao.save(request);

		SmallRequest request2 = new SmallRequest();
		request2.setPhotos(List.of());
		request2.setDescription("won't be done");
		SmallRequest matching = requestDao.save(request2);

		List<SmallRequest> expectedList = List.of(matching);

		// Act
		String result = this.mockMvc
				.perform(get(REQUESTS_URL).contentType(MediaType.APPLICATION_JSON)
						.content(input)
						.headers(TestUtils.userAuthorizationHeader(mockMvc)))
				.andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString(StandardCharsets.UTF_8);

		// Assert
		List<SmallRequest> resultList = mapper.readValue(result,
				new TypeReference<List<SmallRequest>>() {
				});
		assertThat(resultList).usingRecursiveFieldByFieldElementComparator()
				.isEqualTo(expectedList);
	}

	@Test
	public void returnsRequestsEmptySearchByDescription() throws Exception {
		// Arrange
		String input = TestUtils.createJsonString("description", "not present");

		SmallRequest request = new SmallRequest();
		request.setDescription("as if");
		requestDao.save(request);

		SmallRequest request2 = new SmallRequest();
		request2.setDescription("won't be done");
		requestDao.save(request2);

		String expected = "[]";

		// Act
		String result = this.mockMvc
				.perform(get(REQUESTS_URL).contentType(MediaType.APPLICATION_JSON)
						.content(input)
						.headers(TestUtils.userAuthorizationHeader(mockMvc)))
				.andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString(StandardCharsets.UTF_8);

		// Assert
		assertThat(result).isEqualTo(expected);
	}

	@Test
	public void returnsAllRequestsWithSearchByDescription() throws Exception {
		// Arrange
		String input = TestUtils.createJsonString("description", "i");

		SmallRequest request = new SmallRequest();
		request.setPhotos(List.of());
		request.setDescription("as if");
		SmallRequest matching = requestDao.save(request);

		SmallRequest request2 = new SmallRequest();
		request2.setPhotos(List.of());
		request2.setDescription("will not be done");
		SmallRequest matching2 = requestDao.save(request2);

		List<SmallRequest> expectedList = List.of(matching, matching2);

		// Act
		String result = this.mockMvc
				.perform(get(REQUESTS_URL).contentType(MediaType.APPLICATION_JSON)
						.content(input)
						.headers(TestUtils.userAuthorizationHeader(mockMvc)))
				.andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString(StandardCharsets.UTF_8);

		// Assert
		List<SmallRequest> resultList = mapper.readValue(result,
				new TypeReference<List<SmallRequest>>() {
				});
		assertThat(resultList).usingRecursiveFieldByFieldElementComparator()
				.isEqualTo(expectedList);
	}

	@Test
	public void returnsRequestsWithSearchBySite() throws Exception {
		// Arrange
		String input = TestUtils.createJsonString("site", "Couture");

		SmallRequest request = new SmallRequest();
		request.setPhotos(List.of());
		request.setSite(Site.COUTURE);
		SmallRequest matching = requestDao.save(request);

		SmallRequest request2 = new SmallRequest();
		request2.setPhotos(List.of());
		request2.setSite(Site.CONCIERGERIE);
		requestDao.save(request2);

		List<SmallRequest> expectedList = List.of(matching);

		// Act
		String result = this.mockMvc
				.perform(get(REQUESTS_URL).contentType(MediaType.APPLICATION_JSON)
						.content(input)
						.headers(TestUtils.userAuthorizationHeader(mockMvc)))
				.andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString(StandardCharsets.UTF_8);

		// Assert
		List<SmallRequest> resultList = mapper.readValue(result,
				new TypeReference<List<SmallRequest>>() {
				});
		assertThat(resultList).usingRecursiveFieldByFieldElementComparator()
				.isEqualTo(expectedList);
	}
	
	@Test
	public void returnsRequestsWithSearchByStatus() throws Exception {
		// Arrange
		String input = TestUtils.createJsonString("status", "Facturé");

		SmallRequest request = new SmallRequest();
		request.setPhotos(List.of());
		request.setSite(Site.COUTURE);
		request.setStatus(RequestStatus.CLOSED);
		requestDao.save(request);

		SmallRequest request2 = new SmallRequest();
		request2.setPhotos(List.of());
		request2.setStatus(RequestStatus.BILLED);
		request2.setSite(Site.CONCIERGERIE);
		SmallRequest matching = requestDao.save(request2);

		List<SmallRequest> expectedList = List.of(matching);

		// Act
		String result = this.mockMvc
				.perform(get(REQUESTS_URL).contentType(MediaType.APPLICATION_JSON)
						.content(input)
						.headers(TestUtils.userAuthorizationHeader(mockMvc)))
				.andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString(StandardCharsets.UTF_8);

		// Assert
		List<SmallRequest> resultList = mapper.readValue(result,
				new TypeReference<List<SmallRequest>>() {
				});
		assertThat(resultList).usingRecursiveFieldByFieldElementComparator()
				.isEqualTo(expectedList);
	}
	
	@Test
	public void returnsRequestsWithSearchByRequestType() throws Exception {
		// Arrange
		String input = TestUtils.createJsonString("type", "Prestation");

		SmallRequest request = new SmallRequest();
		request.setPhotos(List.of());
		request.setSite(Site.COUTURE);
		request.setType(Service.DONATION);
		requestDao.save(request);

		SmallRequest request2 = new SmallRequest();
		request2.setPhotos(List.of());
		request2.setStatus(RequestStatus.BILLED);
		request2.setType(Service.SERVICE);
		SmallRequest matching = requestDao.save(request2);

		List<SmallRequest> expectedList = List.of(matching);

		// Act
		String result = this.mockMvc
				.perform(get(REQUESTS_URL).contentType(MediaType.APPLICATION_JSON)
						.content(input)
						.headers(TestUtils.userAuthorizationHeader(mockMvc)))
				.andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString(StandardCharsets.UTF_8);

		// Assert
		List<SmallRequest> resultList = mapper.readValue(result,
				new TypeReference<List<SmallRequest>>() {
				});
		assertThat(resultList).usingRecursiveFieldByFieldElementComparator()
				.isEqualTo(expectedList);
	}

	@Test
	public void returnsRequestsWithSearchByClientLastName() throws Exception {
		// Arrange
		SmallClient client = new SmallClient();
		SmallClient client2 = new SmallClient();
		client.setLastName("Dula");
		client2.setLastName("Lars");
		clientDao.save(client);
		clientDao.save(client2);

		SmallRequest request = new SmallRequest();
		SmallRequest request2 = new SmallRequest();
		request.setPhotos(List.of());
		request2.setPhotos(List.of());
		request.setClient(client);
		request2.setClient(client2);

		SmallRequest matching = requestDao.save(request);
		requestDao.save(request2);

		String inputClient = TestUtils.createJsonString("lastName", "ul");
		String input = TestUtils.createJsonString("client", inputClient);
		input = input.replaceAll("\"\\{", "{");
		input = input.replaceAll("\\}\"", "}");

		List<SmallRequest> expectedList = List.of(matching);

		// Act
		String result = this.mockMvc
				.perform(get(REQUESTS_URL).contentType(MediaType.APPLICATION_JSON)
						.content(input)
						.headers(TestUtils.userAuthorizationHeader(mockMvc)))
				.andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString(StandardCharsets.UTF_8);

		// Assert
		List<SmallRequest> resultList = mapper.readValue(result,
				new TypeReference<List<SmallRequest>>() {
				});
		assertThat(resultList).usingRecursiveFieldByFieldElementComparator()
				.isEqualTo(expectedList);
	}
	
	@Test
	public void returnsRequestsWithSearchByDate() throws Exception {
		// Arrange
		SmallClient client = new SmallClient();
		SmallClient client2 = new SmallClient();
		client.setLastName("Dula");
		client2.setLastName("Lars");
		clientDao.save(client);
		clientDao.save(client2);

		SmallRequest request = new SmallRequest();
		SmallRequest request2 = new SmallRequest();
		request.setPhotos(List.of());
		request2.setPhotos(List.of());
		request.setClient(client);
		request2.setClient(client2);
		request.setDate(LocalDate.of(2020, 1, 8));
		request2.setDate(LocalDate.of(2021, 1, 5));

		requestDao.save(request);
		SmallRequest matching = requestDao.save(request2);

		String input = TestUtils.createJsonString("startDate", "02-01-2021", "endDate", "08-01-2021");

		List<SmallRequest> expectedList = List.of(matching);

		// Act
		String result = this.mockMvc
				.perform(get(REQUESTS_URL).contentType(MediaType.APPLICATION_JSON)
						.content(input)
						.headers(TestUtils.userAuthorizationHeader(mockMvc)))
				.andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString(StandardCharsets.UTF_8);

		// Assert
		List<SmallRequest> resultList = mapper.readValue(result,
				new TypeReference<List<SmallRequest>>() {
				});
		assertThat(resultList).usingRecursiveFieldByFieldElementComparator()
				.isEqualTo(expectedList);
	}
	
	@Test
	public void returnsRequestsWithSearchPhoneNumber() throws Exception {
		// Arrange
		SmallClient client = new SmallClient();
		SmallClient client2 = new SmallClient();
		client.setLastName("Dula");
		client2.setLastName("Lars");
		client.setPhoneNumber("456");
		client2.setPhoneNumber("658");
		clientDao.save(client);
		clientDao.save(client2);

		SmallRequest request = new SmallRequest();
		SmallRequest request2 = new SmallRequest();
		request.setPhotos(List.of());
		request2.setPhotos(List.of());
		request.setClient(client);
		request2.setClient(client2);

		SmallRequest matching = requestDao.save(request);
		requestDao.save(request2);

		String inputClient = TestUtils.createJsonString("phoneNumber", "56");
		String input = TestUtils.createJsonString("client", inputClient);
		input = input.replaceAll("\"\\{", "{");
		input = input.replaceAll("\\}\"", "}");
		List<SmallRequest> expectedList = List.of(matching);

		// Act
		String result = this.mockMvc
				.perform(get(REQUESTS_URL).contentType(MediaType.APPLICATION_JSON)
						.content(input)
						.headers(TestUtils.userAuthorizationHeader(mockMvc)))
				.andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString(StandardCharsets.UTF_8);

		// Assert
		List<SmallRequest> resultList = mapper.readValue(result,
				new TypeReference<List<SmallRequest>>() {
				});
		assertThat(resultList).usingRecursiveFieldByFieldElementComparator()
				.isEqualTo(expectedList);
	}
	
	@Test
	public void returnsRequestsWithSearchCity() throws Exception {
		// Arrange
		SmallClient client = new SmallClient();
		SmallClient client2 = new SmallClient();
		client.setLastName("Dula");
		client2.setLastName("Lars");
		client.setPhoneNumber("456");
		client2.setPhoneNumber("658");
		client.setCity("Rennes");
		client2.setCity("Rouen");
		clientDao.save(client);
		clientDao.save(client2);

		SmallRequest request = new SmallRequest();
		SmallRequest request2 = new SmallRequest();
		request.setPhotos(List.of());
		request2.setPhotos(List.of());
		request.setClient(client);
		request2.setClient(client2);

		SmallRequest matching = requestDao.save(request);
		requestDao.save(request2);

		String inputClient = TestUtils.createJsonString("city", "Ren");
		String input = TestUtils.createJsonString("client", inputClient);
		input = input.replaceAll("\"\\{", "{");
		input = input.replaceAll("\\}\"", "}");
		List<SmallRequest> expectedList = List.of(matching);

		// Act
		String result = this.mockMvc
				.perform(get(REQUESTS_URL).contentType(MediaType.APPLICATION_JSON)
						.content(input)
						.headers(TestUtils.userAuthorizationHeader(mockMvc)))
				.andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString(StandardCharsets.UTF_8);

		// Assert
		List<SmallRequest> resultList = mapper.readValue(result,
				new TypeReference<List<SmallRequest>>() {
				});
		assertThat(resultList).usingRecursiveFieldByFieldElementComparator()
				.isEqualTo(expectedList);
	}

}
