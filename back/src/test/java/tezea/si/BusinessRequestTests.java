package tezea.si;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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
import tezea.si.model.business.Enterprise;
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
import tezea.si.utils.HibernateProxyTypeAdapter;
import tezea.si.utils.RequestToDTOConverter;

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

	Enterprise c;
	UserTezea u;
	Estimation e;
	RequestEmployee re;
	Prestation p;
	Site s;
	
	private void clean() {
		requestDao.deleteAll();
		clientDao.deleteAll();
		siteDao.deleteAll();
		userDao.deleteAll();
		prestationDao.deleteAll();
		estimationDao.deleteAll();
		requestEmployeeDao.deleteAll();
	}
	
	//BeforeEach
	private void start1() {
		clean();
		c = new Enterprise();
		c.setAdresse("28 rue de la patate");
		c.setCodePostal("25000");
		c.setEmail("m@m.fr");
		c.setDateAjout(new Date(1234));
		c.setNom("Apple inc.");
		c.setTelephone("+33650505050");
		c.setVille("New York");
		c = clientDao.save(c);

		u = new UserTezea();
		u.setPassword("password");
		u.setUsername("username");
		u.setAuthorities(new ArrayList<String>());
		u = userDao.save(u);

		e = new Estimation();
		e.setAmount(135.0);
		e.setDate(new Date(1235));
		e.setEstimationResponsable(u);
		e.setMaterialEstimation("NO NEED");
		e = estimationDao.save(e);

		re = new RequestEmployee();
		re.setEmail("re@re.fr");
		re.setFirstname("michel");
		re.setLastname("rodriguez");
		re.setHonorificTitle(HonorificTitle.MR);
		re.setPhone("0628282828");
		re = requestEmployeeDao.save(re);

		p = new Prestation();
		p.setDate(new Date(1236));
		p.setDetails("NO DETAILS");
		p.setEmployee(re);
		p.setSatisfactionLevel(SatisfactionLevel.NOT_VERY_SATISFIED);
		p = prestationDao.save(p);

		s = new Site();
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
		//r.setEstimation(e);
		//r.setPrestation(p);
		r.setPriority(Priority.VERY_HIGH);
		r.setResponsable(u);
		r.setSite(s);
		r = requestDao.save(r);
		
		p.setRequest(r);
		e.setRequest(r);
		//UPDATES ALL DETACHED OBJECTS
		p = prestationDao.save(p);
		e = estimationDao.save(e);

		c = (Enterprise) clientDao.findById(c.getId()).get();
		re = requestEmployeeDao.findById(re.getId()).get();
		u = userDao.findById(u.getId()).get();
		s = siteDao.findById(s.getId()).get();
	}

	@Test
	public void testAllRelationMapping() throws Exception {
		this.start1();

		List<Request> list = requestDao.findAll();
		assertEquals(list.size(), 1, "size:" + list.size());

		PrestationRequest test = (PrestationRequest) list.get(0);
		assertNotNull(test.getPriority(), "test priority");
		assertNotNull(test.getDate(), "test date");
		assertNotNull(test.getDescription(), "test desc");
		assertNotNull(test.getClient(), "test client");
		assertNotNull(test.getClient().getRequests(), "test client requests");
		assertNotNull(test.getEstimation().getEstimationResponsable(), "test estim resp");
		assertNotNull(test.getPrestation().getSatisfactionLevel(), "test estim resp");
		assertNotNull(test.getClosedBy(), "test closedby");
		assertNotNull(test.getResponsable(), "test responsable");
		assertEquals(test.getResponsable().getUsername(), u.getUsername(), "test responsable username");
		assertFalse(u.getSites().get(0).getRequests().isEmpty(), "site has updated request");

		assertNotNull(p.getRequest(), "p request");
		assertEquals(p.getRequest().getSite().getResponsable().getUsername(), u.getUsername(), "p req site resp user");
		assertNotNull(p.getDate(), "p date");
		assertNotNull(p.getDetails(), "p details");
		assertNotNull(p.getSatisfactionLevel(), "p satisf");
		
		assertNotNull(u.getUsername(), "u username");
		assertNotNull(u.getPassword(), "u password");
		
		assertNotNull(re.getEmail(), "re email");
		assertNotNull(re.getFirstname(), "re firstname");
		assertNotNull(re.getLastname(), "re lastname");
		assertNotNull(re.getHonorificTitle(), "re honorific title");
		assertNotNull(re.getPhone(), "re phone");
		
		assertNotNull(c.getAdresse(), "c address");
		assertNotNull(c.getCodePostal(), "c getCodePostal");
		assertNotNull(c.getDateAjout(), "c getDateAjout");
		assertNotNull(c.getEmail(), "c getEmail");
		assertNotNull(c.getNom(), "c getNom");
		assertNotNull(c.getTelephone(), "c getTelephone");
		assertNotNull(c.getVille(), "c getVille");
		assertNotNull(c.getRequests(), "c getRequests");
		assertFalse(c.getRequests().isEmpty(), "c getRequests isEmpty");
		assertEquals(c.getRequests().size(), 1, "c getRequests size");

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
		
		assertEquals(test.getDescription(), "NO DESCRIPTION");
	}
	
	//TODO faire en sorte que ce ne soit plus le cas si on souhaite conserver meme si le site n'existe plus
	@Test
	public void testSiteDeletionCascadeOnRequest() throws Exception {
		this.start1();

		List<Request> list = requestDao.findAll();
		assertEquals(list.size(), 1, "size:" + list.size());
		PrestationRequest test = (PrestationRequest) list.get(0);
		
		siteDao.delete(test.getSite());
		
		assertThrows(NoSuchElementException.class, () -> requestDao.findById(test.getId()).get());
		
		assertEquals(siteDao.count(), 0, "not removed s from dao");
	}
	
	@Test
	public void testEstimationAndPrestationDeletionCascade() throws Exception {
		this.start1();

		List<Request> list = requestDao.findAll();
		assertEquals(list.size(), 1, "size:" + list.size());
		PrestationRequest test = (PrestationRequest) list.get(0);
		
		requestDao.delete(test);
		
		assertEquals(estimationDao.count(), 0, "not removed e in cascade");
		assertEquals(prestationDao.count(), 0, "not removed p in cascade");
	}
	
	@Test
	public void testUserTezeaNullIfDeleted() throws Exception {
		this.start1();

		List<Request> list = requestDao.findAll();
		assertEquals(list.size(), 1, "size:" + list.size());
		PrestationRequest test = (PrestationRequest) list.get(0);
		long id = test.getId();
		
		userDao.delete(test.getResponsable()); //repercutions avec @PreRemove
		
		PrestationRequest up = (PrestationRequest) requestDao.findById(id).get();
		
		assertEquals(userDao.count(), 0, "not removed u in dao");
		assertNull(up.getResponsable(), "not null responsable");
	}
	
	@Test
	public void testResponsableUserTezeaNull() throws Exception {
		this.start1();

		List<Request> list = requestDao.findAll();
		assertEquals(list.size(), 1, "size:" + list.size());
		PrestationRequest test = (PrestationRequest) list.get(0);
		long id = test.getId();
		
		test.setResponsable(null);
		test.getSite().setResponsable(null);
		requestDao.save(test);
		siteDao.save(test.getSite());
		
		PrestationRequest up = (PrestationRequest) requestDao.findById(id).get();
		
		assertNotEquals(userDao.count(), 0, "removed u in dao");
		assertNull(up.getResponsable(), "not null responsable");
		assertNull(up.getSite().getResponsable(), "not null site resp");
		assertNotNull(up.getClosedBy(), "null closed by");
	}
	
	@Test
	public void testUserTezeaNullEverywhereUsedIfDeleted() throws Exception {
		this.start1();

		List<Request> list = requestDao.findAll();
		assertEquals(list.size(), 1, "size:" + list.size());
		PrestationRequest test = (PrestationRequest) list.get(0);
		long id = test.getId();
		
		userDao.delete(u); //repercutions avec @PreRemove
		
		PrestationRequest up = (PrestationRequest) requestDao.findById(id).get();
		
		assertEquals(userDao.count(), 0, "not removed u in dao");
		assertNull(up.getResponsable(), "not null responsable");
		assertNull(up.getClosedBy(), "not null closedBy");
		assertNull(up.getSite().getResponsable(), "not null site resp");
	}
	
	@Test
	public void testEntrepriseNullEverywhereUsedIfDeleted() throws Exception {
		this.start1();

		List<Request> list = requestDao.findAll();
		assertEquals(list.size(), 1, "size:" + list.size());
		PrestationRequest test = (PrestationRequest) list.get(0);
		long id = test.getId();
		
		requestDao.delete(test); //repercutions avec @PreRemove
		
		assertThrows(NoSuchElementException.class, () -> requestDao.findById(id).get(), "request not removed");
		assertTrue(u.getResponsabilities().isEmpty(), "user has responsabilities");
		assertTrue(u.getClosedBy().isEmpty(), "user has responsabilities");
		assertTrue(u.getEstimations().isEmpty(), "user has estimations");
		assertTrue(u.getSites().get(0).getRequests().isEmpty(), "site has not updated request");
		assertThrows(NoSuchElementException.class, () -> prestationDao.findById(p.getId()).get());
		assertNotNull(requestEmployeeDao.findById(re.getId()).get(), "request employee deleted");
	}
	
	@Test
	public void jsonTests() {
		this.start1();
		
		List<Request> list = requestDao.findAll();
		assertEquals(list.size(), 1, "size:" + list.size());
		PrestationRequest test = (PrestationRequest) list.get(0);
		
		final GsonBuilder builder = new GsonBuilder()
				.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
				.setPrettyPrinting();
		final Gson gson = builder.create();

		String json = "";
		json = gson.toJson(re);
		logger.info(json);
		json = gson.toJson(e);
		logger.info(json);
		json = gson.toJson(p);
		logger.info(json);
		json = gson.toJson(RequestToDTOConverter.pack(test)); 
		logger.info(json);
		json = gson.toJson(new EnterpriseClientSearchDTO(c)); 
		logger.info(json);
	}
}
