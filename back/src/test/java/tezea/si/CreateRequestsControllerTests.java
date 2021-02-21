package tezea.si;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import tezea.si.dao.SmallClientDAO;
import tezea.si.dao.SmallRequestDAO;
import tezea.si.dao.UserTezeaDAO;
import tezea.si.model.business.SmallClient;
import tezea.si.model.business.UserTezea;
import tezea.si.model.business.request.SmallRequest;
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
		SmallRequest request = new SmallRequest();

		// Act
		this.mockMvc
				.perform(post(REQUESTS_URL).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(request)))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void createEmptyRequestReturnsRequest() throws Exception {
		// Arrange
		SmallRequest request = new SmallRequest();

		// Act
		String response = this.mockMvc
				.perform(post(REQUESTS_URL).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(request))
						.headers(TestUtils.userAuthorizationHeader(mockMvc)))
				.andExpect(status().isCreated()).andReturn().getResponse()
				.getContentAsString();

		// Assert
		SmallRequest result = mapper.readValue(response, SmallRequest.class);
		assertThat(result).usingRecursiveComparison().ignoringFields("id")
				.isEqualTo(request);
		assertThat(result.getId()).isNotZero();
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
		SmallRequest result = mapper.readValue(response, SmallRequest.class);
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
		SmallRequest result = mapper.readValue(response, SmallRequest.class);
		assertThat(result.getId()).isNotZero();
		assertThat(result.getId()).isNotEqualTo(attemptedId);
	}

	@Test
	public void createRequestWithClient() throws Exception {
		// Arrange
		SmallClient client = new SmallClient();
		client.setLastName("Dula");

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
		SmallRequest result = mapper.readValue(response, SmallRequest.class);
		assertThat(result).usingRecursiveComparison().ignoringFields("id", "client.id")
				.isEqualTo(request);
		assertThat(result.getId()).isNotZero();
	}

}
