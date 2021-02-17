package tezea.si;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tezea.si.dao.ClientDAO;
import tezea.si.dao.EstimationDAO;
import tezea.si.dao.PrestationDAO;
import tezea.si.dao.RequestDAO;
import tezea.si.dao.RequestEmployeeDAO;
import tezea.si.dao.SiteDAO;
import tezea.si.dao.UserTezeaDAO;
import tezea.si.model.business.Entreprise;
import tezea.si.model.business.HonorificTitle;
import tezea.si.model.business.RequestEmployee;
import tezea.si.model.business.Site;
import tezea.si.model.business.UserTezea;
import tezea.si.model.business.request.Estimation;
import tezea.si.model.business.request.Prestation;
import tezea.si.model.business.request.PrestationRequest;
import tezea.si.model.business.request.Priority;
import tezea.si.model.business.request.Request;
import tezea.si.model.business.request.SatisfactionLevel;

@SpringBootTest
public class BusinessRequestTests {
	static Logger logger = Logger.getGlobal();

	@Autowired
	RequestDAO requestDao;
	@Autowired
	ClientDAO clientDao;
	@Autowired
	SiteDAO siteDao;
	@Autowired
	UserTezeaDAO userDao;
	@Autowired
	PrestationDAO prestationDao;
	@Autowired
	EstimationDAO estimationDao;
	@Autowired
	RequestEmployeeDAO requestEmployeeDao;

	@Test
	public void registerReturnsOK() throws Exception {
		Entreprise c = new Entreprise();
		c.setAdresse("28 rue de la patate");
		c.setCodePostal("25000");
		c.setEmail("m@m.fr");
		c.setDateAjout(new Date(1234));
		c.setNom("Apple inc.");
		c.setTelephone("+33650505050");
		c.setVille("New York");
		c = clientDao.save(c);

		UserTezea u = new UserTezea();
		u.setPassword("password");
		u.setUsername("username");
		u.setAuthorities(List.of());
		u = userDao.save(u);

		Estimation e = new Estimation();
		e.setAmount(135.0);
		e.setDate(new Date(1235));
		e.setEstimationResponsable(u);
		e.setMaterialEstimation("NO NEED");
		e = estimationDao.save(e);

		RequestEmployee re = new RequestEmployee();
		re.setEmail("re@re.fr");
		re.setFirstname("michel");
		re.setLastname("rodriguez");
		re.setHonorificTitle(HonorificTitle.MR);
		re.setPhone("0628282828");
		re = requestEmployeeDao.save(re);

		Prestation p = new Prestation();
		p.setDate(new Date(1236));
		p.setDetails("NO DETAILS");
		p.setEmployee(re);
		p.setSatisfactionLevel(SatisfactionLevel.NOT_VERY_SATISFIED);
		p = prestationDao.save(p);

		Site s = new Site();
		s.setDescription("CONCIERGERIE DESCRIPTION");
		s.setResponsable(u);
		s.setNom("CONCIERGERIE");
		s.setTelephone("0707070707");
		s = siteDao.save(s);

		PrestationRequest r = new PrestationRequest();
		r.setClient(c);
		r.setClosedBy(u);
		r.setDate(new Date(3210));
		r.setDescription("NO DESCRIPTION");
		r.setEstimation(e);
		r.setPrestation(p);
		r.setPriority(Priority.VERY_HIGH);
		r.setResponsable(u);
		r.setSite(s);
		r = requestDao.save(r);

		p.setRequest(r);
		e.setRequest(r);
		p = prestationDao.save(p);
		e = estimationDao.save(e);

		Request test = (PrestationRequest)requestDao.findAll().get(0);

		//try {
			logger.info(test.getPriority().getMessage());
			logger.info(test.getDate().toGMTString());
			logger.info(test.getDescription());
			logger.info(test.getClient().getEmail());
			logger.info(p.getRequest().getDescription());
			logger.info(test.getSite().getNom());
			//throws exception...
			assertNotNull(test.getClosedBy(), "usertezea is null (1)");
			assertNotNull(test.getResponsable(), "usertezea is null (2)");
			logger.info(test.getClosedBy().getUsername());
			logger.info(test.getResponsable().getUsername());

			/*final GsonBuilder builder = new GsonBuilder()
					.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
					.setPrettyPrinting();
			final Gson gson = builder.create();

			String json = gson.toJson(test);
			logger.info(json);*/
		/*} catch (Exception ex) {
			logger.info(ex.toString());
			throw ex;
		}*/

		assertEquals(test.getDescription(), "NO DESCRIPTION");
	}
}
