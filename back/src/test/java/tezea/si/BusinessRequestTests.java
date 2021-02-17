package tezea.si;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
import tezea.si.model.dto.EnterpriseClientSearchDTO;
import tezea.si.model.dto.RequestsSearchDTO;
import tezea.si.utils.HibernateProxyTypeAdapter;

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

	/*
	 * @BeforeAll public void clean() { requestDao.deleteAll();
	 * clientDao.deleteAll(); siteDao.deleteAll(); userDao.deleteAll();
	 * prestationDao.deleteAll(); estimationDao.deleteAll();
	 * requestEmployeeDao.deleteAll(); }
	 */

	@Test
	public void testAllRelationMapping() throws Exception {
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
		u.setAuthorities(new ArrayList<String>());
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
		c = (Entreprise) clientDao.findById(c.getId()).get();
		re = requestEmployeeDao.findById(re.getId()).get();
		u = userDao.findById(u.getId()).get();
		s = siteDao.findById(s.getId()).get();

		List<Request> list = requestDao.findAll();
		Request test = (PrestationRequest) list.get(0);
		assertEquals(list.size(), 1, "size:" + list.size());

		assertNotNull(test.getPriority());
		assertNotNull(test.getDate());
		assertNotNull(test.getDescription());
		assertNotNull(test.getClient());
		assertNotNull(test.getClosedBy(), "test closedby");
		assertNotNull(test.getResponsable(), "test responsable");
		assertEquals(test.getResponsable().getUsername(), u.getUsername(), "test responsable username");
		assertNotNull(p.getRequest());
		assertEquals(p.getRequest().getSite().getResponsable().getUsername(), u.getUsername(), "p req site resp user");
		assertNotNull(p.getDate());
		assertNotNull(p.getDetails());
		assertNotNull(p.getSatisfactionLevel());
		assertNotNull(u.getUsername());
		assertNotNull(u.getPassword());
		assertNotNull(re.getEmail());
		assertNotNull(re.getFirstname());
		assertNotNull(re.getLastname());
		assertNotNull(re.getHonorificTitle());
		assertNotNull(re.getPhone());
		assertNotNull(c.getAdresse());
		assertNotNull(c.getCodePostal());
		assertNotNull(c.getDateAjout());
		assertNotNull(c.getEmail());
		assertNotNull(c.getNom());
		assertNotNull(c.getTelephone());
		assertNotNull(c.getVille());
		assertNotNull(c.getRequests());
		assertFalse(c.getRequests().isEmpty());
		assertEquals(c.getRequests().size(), 1);

		assertNotNull(e.getAmount(), "e no amount");
		assertNotNull(e.getDate(), "e no date");
		assertNotNull(e.getEstimationResponsable(), "e no resps");

		assertNotNull(s.getDescription(), "s no desc");
		assertNotNull(s.getNom(), "s no name");
		assertNotNull(s.getRequests(), "s no requests");
		assertNotNull(s.getResponsable(), "s no resp");
		assertNotNull(s.getTelephone(), "s no tel");

		logger.info(test.getPriority().getMessage());
		logger.info(test.getDate().toGMTString());
		logger.info(test.getDescription());
		logger.info(test.getClient().getEmail());
		logger.info(p.getRequest().getDescription());
		logger.info(test.getSite().getNom());
		logger.info(test.getClosedBy().getUsername());
		logger.info(test.getResponsable().getUsername());

		final GsonBuilder builder = new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
				.setPrettyPrinting();
		final Gson gson = builder.create();

		String json = gson.toJson(u);
		logger.info(json);
		json = gson.toJson(re);
		logger.info(json);
		json = gson.toJson(e);
		logger.info(json);
		json = gson.toJson(p);
		logger.info(json);
		json = gson.toJson(u);
		logger.info(json);

		json = gson.toJson(new EnterpriseClientSearchDTO(c));
		logger.info(json);
		/*
		 * json = gson.toJson(new RequestsSearchDTO(test)); logger.info(json);
		 */

		assertEquals(test.getDescription(), "NO DESCRIPTION");
	}
}
