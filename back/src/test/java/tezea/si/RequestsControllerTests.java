package tezea.si;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import tezea.si.dao.UserTezeaDAO;
import tezea.si.model.business.UserTezea;

@SpringBootTest
@AutoConfigureMockMvc
public class RequestsControllerTests {
	@Autowired
	UserTezeaDAO userDao;

	@BeforeEach
	public void before() {

		UserTezea user = new UserTezea();
		user.setUsername("jean");
		user.setPassword("$2a$10$sDP3s/p0M1TCOW6FizwLWulsnwT2BryFkLHqKusRRljaYKYOWVE7u");

		userDao.save(user);
	}

	@Test
	public void aa() {
		assertThat(true).isTrue();
	}
}
