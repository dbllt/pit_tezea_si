package tezea.si.utils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {
	public static final String REGISTER_URL = "/register";
	public static final String AUTH_URL = "/auth/authenticate";
	public static final String REFRESH_URL = "/auth/token";

	@Autowired
	static ObjectMapper objectMapper;

	@Autowired
	static MockMvc mockMvc;

	public static String createJsonString(String... strings)
			throws IllegalArgumentException {
		if (strings.length % 2 != 0)
			throw new IllegalArgumentException("Should have even number of parameter");

		List<String> jsonData = new ArrayList<>();
		for (int i = 0; i < strings.length; i += 2) {
			jsonData.add("\"" + strings[i] + "\":\"" + strings[i + 1] + "\"");
		}

		return "{" + String.join(",", jsonData) + "}";
	}

	public static Map<String, String> getJsonAsMap(String json) throws Exception {
		TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {
		};
		return objectMapper.readValue(json, typeRef);
	}

	public static Map<String, String> authenticateAdmin() throws Exception {
		return authenticate("grogu", "password");
	}

	public static Map<String, String> authenticateUser() throws Exception {
		return authenticate("jean", "password");
	}

	public static Map<String, String> authenticate(String username, String password)
			throws Exception {
		String authForm = createJsonString("username", username, "password", password);

		Map<String, String> tokenMap = getJsonAsMap(mockMvc
				.perform(post(AUTH_URL).contentType(MediaType.APPLICATION_JSON)
						.content(authForm))
				.andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString());
		return tokenMap;
	}

	public static HttpHeaders adminAuthorizationHeader() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + authenticateAdmin().get("token"));
		return headers;
	}

	public static HttpHeaders userAuthorizationHeader() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + authenticateUser().get("token"));
		return headers;
	}

	public static String adminRefreshToken() throws Exception {
		return authenticateAdmin().get("refreshToken");
	}
}
