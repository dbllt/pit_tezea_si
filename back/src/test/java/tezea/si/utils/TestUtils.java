package tezea.si.utils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {
	public static final String REGISTER_URL = "/register";
	public static final String AUTH_URL = "/auth/authenticate";
	public static final String REFRESH_URL = "/auth/token";

	static ObjectMapper objectMapper = new ObjectMapper();

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

	public static String getValueFromJson(String field, String json) {
		Pattern pattern = Pattern.compile("\"" + field + "\":\"(.*?)\"");
		Matcher matcher = pattern.matcher(json);
		if (matcher.find()) {
			return matcher.group(1);
		}
		throw new RuntimeException("Could not find field " + field + " in json " + json);
	}

	public static Map<String, String> getJsonAsMap(String json) throws Exception {
		TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {
		};
		return objectMapper.readValue(json, typeRef);
	}

	public static Map<String, String> authenticateAdmin(MockMvc mockMvc)
			throws Exception {
		return authenticate("grogu", "password", mockMvc);
	}

	public static Map<String, String> authenticateUser(MockMvc mockMvc) throws Exception {
		return authenticate("jean", "password", mockMvc);
	}

	public static Map<String, String> authenticate(String username, String password,
			MockMvc mockMvc) throws Exception {
		String authForm = createJsonString("username", username, "password", password);

		String result = mockMvc
				.perform(post(AUTH_URL).contentType(MediaType.APPLICATION_JSON)
						.content(authForm))
				.andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString();
		Map<String, String> tokenMap = new HashMap<>();
		tokenMap.put("token", getValueFromJson("token", result));
		tokenMap.put("refreshToken", getValueFromJson("refreshToken", result));
		return tokenMap;
	}

	public static HttpHeaders adminAuthorizationHeader(MockMvc mockMvc) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + authenticateAdmin(mockMvc).get("token"));
		return headers;
	}

	public static HttpHeaders userAuthorizationHeader(MockMvc mockMvc) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + authenticateUser(mockMvc).get("token"));
		return headers;
	}

	public static String adminRefreshToken(MockMvc mockMvc) throws Exception {
		return authenticateAdmin(mockMvc).get("refreshToken");
	}
}
