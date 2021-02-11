package tezea.si;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void shouldNotAllowAccessWithoutRegistering() throws Exception {
		// Arrange
		String url = "/hello";

		// Act, assert
		this.mockMvc.perform(get(url)).andExpect(status().isUnauthorized());
	}

	@Test
	public void authenticationReturnsTokens() throws Exception {
		// Arrange
		String authForm = "{\"username\":\"grogu\", \"password\":\"password\"}";
		String url = "/auth/authenticate";

		// Act
		String result = this.mockMvc
				.perform(post(url).contentType(MediaType.APPLICATION_JSON)
						.content(authForm))
				.andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString();

		// Assert
		Map<String, String> resultMap = getJsonAsMap(result);
		assertThat(resultMap).hasFieldOrProperty("refreshToken");
		assertThat(resultMap.get("refreshToken")).hasSizeGreaterThan(0);
		assertThat(resultMap).hasFieldOrProperty("token");
		assertThat(resultMap.get("token")).hasSizeGreaterThan(0);
	}

	@Test
	public void authenticationFailsWrongPassword() throws Exception {
		// Arrange
		String authForm = "{\"username\":\"grogu\", \"password\":\"wrongpassword\"}";
		String url = "/auth/authenticate";

		// Act
		this.mockMvc.perform(
				post(url).contentType(MediaType.APPLICATION_JSON).content(authForm))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void authenticationFailsWrongUser() throws Exception {
		// Arrange
		String authForm = "{\"username\":\"notgrogu\", \"password\":\"password\"}";
		String url = "/auth/authenticate";

		// Act
		this.mockMvc.perform(
				post(url).contentType(MediaType.APPLICATION_JSON).content(authForm))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void shouldAllowAccessUsingToken() throws Exception {
		// Arrange
		String url = "/hello";

		// Act, assert
		this.mockMvc.perform(get(url).header("Authorization", validAuthorizationHeader()))
				.andExpect(status().isOk());
	}

	@Test
	public void canRefreshToken() throws Exception {
		// Arrange
		Map<String, String> authentication = authenticate();
		String refreshToken = authentication.get("refreshToken");
		String oldToken = authentication.get("token");
		String form = "{\"refreshToken\":\"" + refreshToken + "\"}";
		String url = "/auth/token";
		Thread.sleep(1000); // the new token is same as old one if refreshing too fast

		// Act
		String result = this.mockMvc
				.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(form))
				.andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString();

		// Assert
		Map<String, String> resultMap = getJsonAsMap(result);
		assertThat(resultMap).hasFieldOrProperty("token");
		String newToken = resultMap.get("token");
		assertThat(newToken).hasSizeGreaterThan(0);
		assertThat(newToken).isNotEqualTo(oldToken);
	}

	@Test
	public void cannotRefreshInvalidToken() throws Exception {
		// Arrange
		String form = "{\"refreshToken\":\"invalidToken\"}";
		String url = "/auth/token";

		// Act
		this.mockMvc
				.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(form))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void canUseNewToken() throws Exception {
		// Arrange
		String refreshToken = validRefreshToken();
		String form = "{\"refreshToken\":\"" + refreshToken + "\"}";
		String newToken = getJsonAsMap(this.mockMvc
				.perform(post("/auth/token").contentType(MediaType.APPLICATION_JSON)
						.content(form))
				.andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString()).get("token");
		String url = "/hello";

		// Act, assert
		this.mockMvc.perform(get(url).header("Authorization", "Bearer " + newToken))
				.andExpect(status().isOk());
	}

	@Test
	public void invalidateRefreshToken() throws Exception {
		// Arrange
		String refreshToken = validRefreshToken();
		String form = "{\"refreshToken\":\"" + refreshToken + "\"}";
		String url = "/auth/token";

		// Act
		this.mockMvc
				.perform(put(url).contentType(MediaType.APPLICATION_JSON).content(form))
				.andExpect(status().isNoContent());

		// Assert: cannot refresh again
		this.mockMvc
				.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(form))
				.andExpect(status().isUnauthorized());
	}
	
	@Test
	public void cannotInvalidateInvalidRefreshToken() throws Exception {
		// Arrange
		String form = "{\"refreshToken\":\"invalidToken\"}";
		String url = "/auth/token";

		// Act, assert
		this.mockMvc
				.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(form))
				.andExpect(status().isUnauthorized());
	}

	private Map<String, String> getJsonAsMap(String json) throws Exception {
		TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {
		};
		return objectMapper.readValue(json, typeRef);
	}

	private Map<String, String> authenticate() throws Exception {
		String authForm = "{\"username\":\"grogu\", \"password\":\"password\"}";
		String url = "/auth/authenticate";

		Map<String, String> tokenMap = getJsonAsMap(this.mockMvc
				.perform(post(url).contentType(MediaType.APPLICATION_JSON)
						.content(authForm))
				.andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString());
		return tokenMap;
	}

	private String validAuthorizationHeader() throws Exception {
		String token = authenticate().get("token");
		return "Bearer " + token;
	}

	private String validRefreshToken() throws Exception {
		return authenticate().get("refreshToken");
	}
}
