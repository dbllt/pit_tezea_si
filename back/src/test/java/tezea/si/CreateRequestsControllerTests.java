package tezea.si;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import tezea.si.dao.SiteDAO;
import tezea.si.dao.SmallClientDAO;
import tezea.si.dao.SmallRequestDAO;
import tezea.si.dao.UserTezeaDAO;
import tezea.si.model.SmallClientDTO;
import tezea.si.model.SmallRequestDTO;
import tezea.si.model.SmallSiteDTO;
import tezea.si.model.SmallUserDTO;
import tezea.si.model.business.HonorificTitle;
import tezea.si.model.business.Site;
import tezea.si.model.business.SmallClient;
import tezea.si.model.business.UserTezea;
import tezea.si.model.business.request.Priority;
import tezea.si.model.business.request.RequestStatus;
import tezea.si.model.business.request.SatisfactionLevel;
import tezea.si.model.business.request.Service;
import tezea.si.model.business.request.SmallRequest;
import tezea.si.model.business.request.TimeUnit;
import tezea.si.utils.TestUtils;

@SpringBootTest
@AutoConfigureMockMvc
public class CreateRequestsControllerTests {

	private static final String REQUESTS_URL = "/requests";

	@Autowired
	UserTezeaDAO userDao;

	@Autowired
	SmallRequestDAO requestDao;

	@Autowired
	SmallClientDAO clientDao;

	@Autowired
	SiteDAO siteDao;

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

		Site site = new Site();
		site.setNom("conciergerie");
		siteDao.save(site);
	}

	@AfterEach
	public void destroy() {
		requestDao.deleteAll();
		siteDao.deleteAll();
		userDao.deleteAll();
	}

	@Test
	public void cannotAccessWithoutToken() throws Exception {
		// Arrange
		SmallRequest request = new SmallRequest();

		// Act
		this.mockMvc
				.perform(post(REQUESTS_URL).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(request)))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void createEmptyRequestReturnsRequestAndSetsLastUpdated() throws Exception {
		// Arrange
		SmallRequest request = new SmallRequest();
		SmallRequestDTO expected = new SmallRequestDTO();

		// Act
		String response = this.mockMvc
				.perform(post(REQUESTS_URL).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(request))
						.headers(TestUtils.userAuthorizationHeader(mockMvc)))
				.andExpect(status().isCreated()).andReturn().getResponse()
				.getContentAsString();

		// Assert
		SmallRequestDTO result = mapper.readValue(response, SmallRequestDTO.class);
		assertThat(result).usingRecursiveComparison().ignoringFields("id", "lastUpdated")
				.isEqualTo(expected);
		assertThat(result.getId()).isNotZero();
		assertThat(result.getLastUpdated()).isNotNull();
	}

	@Test
	public void cannotSetRequestId() throws Exception {
		// Arrange
		SmallRequest request = new SmallRequest();
		Long attemptedId = 56L;
		request.setId(attemptedId);

		// Act
		String response = this.mockMvc
				.perform(post(REQUESTS_URL).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(request))
						.headers(TestUtils.userAuthorizationHeader(mockMvc)))
				.andExpect(status().isCreated()).andReturn().getResponse()
				.getContentAsString();

		// Assert
		SmallRequestDTO result = mapper.readValue(response, SmallRequestDTO.class);
		assertThat(result.getId()).isNotZero();
		assertThat(result.getId()).isNotEqualTo(attemptedId);
	}

	@Test
	public void cannotSetClientId() throws Exception {
		// Arrange
		SmallClient client = new SmallClient();
		client.setLastName("Dula");
		Long attemptedId = 78L;
		client.setId(attemptedId);

		SmallRequest request = new SmallRequest();
		request.setClient(client);

		// Act
		String response = this.mockMvc
				.perform(post(REQUESTS_URL).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(request))
						.headers(TestUtils.userAuthorizationHeader(mockMvc)))
				.andExpect(status().isCreated()).andReturn().getResponse()
				.getContentAsString();

		// Assert
		SmallRequestDTO result = mapper.readValue(response, SmallRequestDTO.class);
		assertThat(result.getId()).isNotZero();
		assertThat(result.getId()).isNotEqualTo(attemptedId);
	}

	@Test
	public void createRequestWithClient() throws Exception {
		// Arrange
		String name = "Dula";
		SmallClient client = new SmallClient();
		client.setLastName(name);

		SmallRequest request = new SmallRequest();
		request.setClient(client);

		SmallRequestDTO expected = new SmallRequestDTO();
		SmallClientDTO expectedClient = new SmallClientDTO();
		expectedClient.setLastName(name);
		expected.setClient(expectedClient);

		// Act
		String response = this.mockMvc
				.perform(post(REQUESTS_URL).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(request))
						.headers(TestUtils.userAuthorizationHeader(mockMvc)))
				.andExpect(status().isCreated()).andReturn().getResponse()
				.getContentAsString();

		// Assert
		SmallRequestDTO result = mapper.readValue(response, SmallRequestDTO.class);
		assertThat(result).usingRecursiveComparison()
				.ignoringFields("id", "client.id", "lastUpdated").isEqualTo(expected);
		assertThat(result.getId()).isNotZero();
	}

	@Test
	public void createFullRequest() throws Exception {
		// Arrange
		String siteName = "conciergerie";
		String username = "jean";
		String access = "difficult";
		String description = "some service";
		String address = "45 rue";
		int reps = 5;
		int wood = 12;
		int donated = 50;
		LocalDate date = LocalDate.now();

		Site site = new Site();
		site.setNom(siteName);

		UserTezea user = new UserTezea();
		user.setUsername(username);

		SmallClient client = new SmallClient();
		client.setAddress(address);
		
		SmallRequest request = new SmallRequest();
		request.setAccessDetails(access);
		request.setDescription(description);
		request.setDate(date);
		request.setRepetitionTime(reps);
		request.setRepetitionUnit(TimeUnit.MONTH);
		// ?? maybe not let client set this
		request.setStatus(RequestStatus.NEW);
		request.setPriority(Priority.HIGH);
		request.setAmountWood(wood);
		request.setAmountDonated(donated);
		request.setAppointmentPlasmaDate(date);
		request.setSatisfactionLevel(SatisfactionLevel.NOT_SATISFIED);
		request.setType(Service.DONATION);

		request.setClient(client);
		request.setSite(site);
		request.setResponsable(user);
		request.setClosedBy(user);
		request.setLastUpdatedBy(user);

//		private SmallEstimation estimation;

		SmallSiteDTO expectedSite = new SmallSiteDTO();
		expectedSite.setName(siteName);

		SmallUserDTO expectedUser = new SmallUserDTO();
		expectedUser.setUsername(username);
		
		SmallClientDTO expectedClient = new SmallClientDTO();
		expectedClient.setAddress(address);

		SmallRequestDTO expected = new SmallRequestDTO();
		expected.setAccessDetails(access);
		expected.setDescription(description);
		expected.setDate(date);
		expected.setRepetitionTime(reps);
		expected.setRepetitionUnit(TimeUnit.MONTH);
		expected.setStatus(RequestStatus.NEW);
		expected.setPriority(Priority.HIGH);
		expected.setAmountWood(wood);
		expected.setAmountDonated(donated);
		expected.setAppointmentPlasmaDate(date);
		expected.setSatisfactionLevel(SatisfactionLevel.NOT_SATISFIED);
		expected.setType(Service.DONATION);

		expected.setClient(expectedClient);
		expected.setSite(expectedSite);
		expected.setResponsable(expectedUser);
		expected.setClosedBy(expectedUser);
		expected.setLastUpdatedBy(expectedUser);

		// Act
		String response = this.mockMvc
				.perform(post(REQUESTS_URL).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(request))
						.headers(TestUtils.userAuthorizationHeader(mockMvc)))
				.andExpect(status().isCreated()).andReturn().getResponse()
				.getContentAsString();

		// Assert
		SmallRequestDTO result = mapper.readValue(response, SmallRequestDTO.class);
		assertThat(result).usingRecursiveComparison().ignoringFields("id", "lastUpdated")
				.isEqualTo(expected);
		assertThat(result.getId()).isNotZero();
		assertThat(result.getLastUpdated()).isNotNull();
	}

	// todo: test cannot add request with unknown user, or with unknown site
	// todo: test cannot set request state, is new by default
	// todo: test cannot set lastupdated date

}
