package tezea.si;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import tezea.si.utils.TestUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import tezea.si.dao.UserTezeaDAO;
import tezea.si.model.business.UserTezea;
import tezea.si.utils.auth.GrantedAutorities;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
// ^ reinitialize database after each test
class AuthenticationTests {

    private static final String REGISTER_URL = "/register";
    private static final String AUTH_URL = "/auth/authenticate";
    private static final String REFRESH_URL = "/auth/token";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    UserTezeaDAO userDao;

    @BeforeEach
    public void before() {
        UserTezea admin = new UserTezea();
        admin.setUsername("grogu");
        admin.setPassword("$2a$10$sDP3s/p0M1TCOW6FizwLWulsnwT2BryFkLHqKusRRljaYKYOWVE7u");
        admin.setAuthorities(List.of(GrantedAutorities.ADMIN));

        userDao.save(admin);
        
        UserTezea user = new UserTezea();
        user.setUsername("jean");
        user.setPassword("$2a$10$sDP3s/p0M1TCOW6FizwLWulsnwT2BryFkLHqKusRRljaYKYOWVE7u");
        
        userDao.save(user);
    }

    @Test
    public void shouldNotAllowAccessWithoutAuth() throws Exception {
        // Arrange
        String url = "/hello";

        // Act, assert
        this.mockMvc.perform(get(url)).andExpect(status().isUnauthorized());
    }

    @Test
    public void registerAllowsOnlyAdmin() throws Exception {
        // Arrange
        String form = TestUtils.createJsonString("username", "newuser", "password", "thepassword");

        // Act, assert
        
        // No auth
        this.mockMvc.perform(post(REGISTER_URL).contentType(MediaType.APPLICATION_JSON).content(form))
                .andExpect(status().isUnauthorized());
        
        // Simple user
        this.mockMvc.perform(post(REGISTER_URL).headers(TestUtils.userAuthorizationHeader())
                .contentType(MediaType.APPLICATION_JSON).content(form)).andExpect(status().isUnauthorized());

        // Admin
        this.mockMvc.perform(post(REGISTER_URL).headers(TestUtils.adminAuthorizationHeader())
                .contentType(MediaType.APPLICATION_JSON).content(form)).andExpect(status().isCreated());
    }

    @Test
    public void authenticationReturnsTokens() throws Exception {
        // Arrange
        String form = TestUtils.createJsonString("username", "grogu", "password", "password");

        // Act
        String result = this.mockMvc.perform(post(AUTH_URL).contentType(MediaType.APPLICATION_JSON).content(form))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        // Assert
        Map<String, String> resultMap = TestUtils.getJsonAsMap(result);
        assertThat(resultMap).hasFieldOrProperty("refreshToken");
        assertThat(resultMap.get("refreshToken")).hasSizeGreaterThan(0);
        assertThat(resultMap).hasFieldOrProperty("token");
        assertThat(resultMap.get("token")).hasSizeGreaterThan(0);
    }

    @Test
    public void authenticationFailsWrongPassword() throws Exception {
        // Arrange
        String form = TestUtils.createJsonString("username", "grogu", "password", "wrongpassword");
        // Act
        this.mockMvc.perform(post(AUTH_URL).contentType(MediaType.APPLICATION_JSON).content(form))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void authenticationFailsWrongUser() throws Exception {
        // Arrange
        String form = TestUtils.createJsonString("username", "notgrogu", "password", "password");

        // Act
        this.mockMvc.perform(post(AUTH_URL).contentType(MediaType.APPLICATION_JSON).content(form))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldAllowAccessUsingToken() throws Exception {
        // Arrange
        String url = "/hello";

        // Act, assert
        this.mockMvc.perform(get(url).headers(TestUtils.adminAuthorizationHeader())).andExpect(status().isOk());
    }

    @Test
    public void shouldNotAllowAccessUsingRefreshToken() throws Exception {
        // Arrange
        String url = "/hello";

        // Act, assert
        this.mockMvc.perform(get(url).header("Authorization", "Bearer " + TestUtils.adminRefreshToken()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void canRefreshToken() throws Exception {
        // Arrange
        Map<String, String> authentication = TestUtils.authenticateAdmin();
        String refreshToken = authentication.get("refreshToken");
        String oldToken = authentication.get("token");
        String form = TestUtils.createJsonString("refreshToken", refreshToken);
        Thread.sleep(1000); // the new token is same as old one if refreshing too fast

        // Act
        String result = this.mockMvc.perform(post(REFRESH_URL).contentType(MediaType.APPLICATION_JSON).content(form))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        // Assert
        Map<String, String> resultMap = TestUtils.getJsonAsMap(result);
        assertThat(resultMap).hasFieldOrProperty("token");
        String newToken = resultMap.get("token");
        assertThat(newToken).hasSizeGreaterThan(0);
        assertThat(newToken).isNotEqualTo(oldToken);
    }

    @Test
    public void cannotRefreshInvalidToken() throws Exception {
        // Arrange
        String form = TestUtils.createJsonString("refreshToken", "invalidToken");

        // Act
        this.mockMvc.perform(post(REFRESH_URL).contentType(MediaType.APPLICATION_JSON).content(form))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void canUseNewToken() throws Exception {
        // Arrange
        String refreshToken = TestUtils.adminRefreshToken();
        String form = TestUtils.createJsonString("refreshToken", refreshToken);
        String newToken = TestUtils.getJsonAsMap(
                this.mockMvc.perform(post(REFRESH_URL).contentType(MediaType.APPLICATION_JSON).content(form))
                        .andExpect(status().isOk()).andReturn().getResponse().getContentAsString()).get("token");
        String url = "/hello";

        // Act, assert
        this.mockMvc.perform(get(url).header("Authorization", "Bearer " + newToken)).andExpect(status().isOk());
    }

    @Test
    public void invalidateRefreshToken() throws Exception {
        // Arrange
        String refreshToken = TestUtils.adminRefreshToken();
        String form = TestUtils.createJsonString("refreshToken", refreshToken);

        // Act
        this.mockMvc.perform(put(REFRESH_URL).contentType(MediaType.APPLICATION_JSON).content(form))
                .andExpect(status().isNoContent());

        // Assert: cannot refresh again
        this.mockMvc.perform(post(REFRESH_URL).contentType(MediaType.APPLICATION_JSON).content(form))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void cannotInvalidateInvalidRefreshToken() throws Exception {
        // Arrange
        String form = TestUtils.createJsonString("refreshToken", "invalidToken");

        // Act, assert
        this.mockMvc.perform(post(REFRESH_URL).contentType(MediaType.APPLICATION_JSON).content(form))
                .andExpect(status().isUnauthorized());
    }

}
