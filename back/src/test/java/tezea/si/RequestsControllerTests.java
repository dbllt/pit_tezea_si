package tezea.si;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import tezea.si.dao.UserTezeaDAO;
import tezea.si.model.business.UserTezea;
import tezea.si.utils.TestUtils;

@SpringBootTest
@AutoConfigureMockMvc
public class RequestsControllerTests {
	@Autowired
	UserTezeaDAO userDao;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void before() {

		UserTezea user = new UserTezea();
		user.setUsername("jean");
		user.setPassword("$2a$10$sDP3s/p0M1TCOW6FizwLWulsnwT2BryFkLHqKusRRljaYKYOWVE7u");

		userDao.save(user);
	}



}
