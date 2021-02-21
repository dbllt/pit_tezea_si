package tezea.si;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import tezea.si.dao.SmallClientDAO;
import tezea.si.model.business.SmallClient;
import tezea.si.model.business.SmallClient_;
import tezea.si.model.dto.SmallClientSearchDTO;
import tezea.si.service.SmallClientSearchService;
import tezea.si.service.SpecificationBuilder;
import tezea.si.utils.search.SearchCriteria;
import tezea.si.utils.search.SearchOperations;
import tezea.si.utils.search.SearchSpecification;

@SpringBootTest
public class ClientSearchTests {

	@Autowired
	private SmallClientDAO clientDao;

	@Autowired
	private SpecificationBuilder<SmallClient> builder;

	@Autowired
	private SmallClientSearchService searchService;

	private SmallClient jeanNoix35700;
	private SmallClient jeannetteNoisette35200;
	private SmallClient liseOlli53500;

	@BeforeEach
	public void init() {
		SmallClient SmallClient = new SmallClient();
		SmallClient.setLastName("Noix");
		SmallClient.setFirstName("Jean");
		SmallClient.setPostCode("35700");
		SmallClient.setCity("rennes");
		jeanNoix35700 = clientDao.save(SmallClient);
		SmallClient SmallClient2 = new SmallClient();
		SmallClient2.setLastName("Noisette");
		SmallClient2.setFirstName("Jeannette");
		SmallClient2.setPostCode("35200");
		SmallClient2.setCity("rennes");
		jeannetteNoisette35200 = clientDao.save(SmallClient2);
		SmallClient SmallClient3 = new SmallClient();
		SmallClient3.setLastName("Olli");
		SmallClient3.setFirstName("Lise");
		SmallClient3.setPostCode("53500");
		SmallClient3.setCity("ailleurs");
		liseOlli53500 = clientDao.save(SmallClient3);
	}

	@AfterEach
	public void destroy() {
		clientDao.deleteAll();
	}

	@Test
	public void findByPostCodeContains() {
		// Arrange
		SearchSpecification<SmallClient> spec = new SearchSpecification<SmallClient>(
				new SearchCriteria<SmallClient>(SmallClient_.postCode, SearchOperations.CONTAINS,
						"7"));

		// Act
		List<SmallClient> results = clientDao.findAll(spec);

		// Assert
		assertThat(results).usingRecursiveFieldByFieldElementComparator()
				.containsOnly(jeanNoix35700);
	}

	@Test
	public void findByPostCodeStartsWith() {
		// Arrange
		SearchSpecification<SmallClient> spec = new SearchSpecification<SmallClient>(
				new SearchCriteria<SmallClient>(SmallClient_.postCode,
						SearchOperations.STARTSWITH, "35"));

		// Act
		List<SmallClient> results = clientDao.findAll(spec);

		// Assert
		assertThat(results).usingRecursiveFieldByFieldElementComparator()
				.containsOnly(jeanNoix35700, jeannetteNoisette35200);
	}

	@Test
	public void findEmptyByPostCodeContains() {
		// Arrange
		SearchSpecification<SmallClient> spec = new SearchSpecification<SmallClient>(
				new SearchCriteria<SmallClient>(SmallClient_.postCode, SearchOperations.CONTAINS,
						"5"));

		// Act
		List<SmallClient> results = clientDao.findAll(spec);

		// Assert
		assertThat(results).usingRecursiveFieldByFieldElementComparator()
				.containsOnly(jeanNoix35700, jeannetteNoisette35200, liseOlli53500);
	}

	@Test
	public void findEmptyByPostCodeEquals() {
		// Arrange
		SearchSpecification<SmallClient> spec = new SearchSpecification<SmallClient>(
				new SearchCriteria<SmallClient>(SmallClient_.postCode, SearchOperations.EQUALS,
						"700"));

		// Act
		List<SmallClient> results = clientDao.findAll(spec);

		// Assert
		assertThat(results).usingRecursiveFieldByFieldElementComparator().isEmpty();
	}

	@Test
	public void findByPostCodeEquals() {
		// Arrange
		SearchSpecification<SmallClient> spec = new SearchSpecification<SmallClient>(
				new SearchCriteria<SmallClient>(SmallClient_.postCode, SearchOperations.EQUALS,
						"35700"));

		// Act
		List<SmallClient> results = clientDao.findAll(spec);

		// Assert
		assertThat(results).usingRecursiveFieldByFieldElementComparator()
				.containsOnly(jeanNoix35700);
	}

	@Test
	public void findWithBuiltSpec() {
		// Act
		Specification<SmallClient> spec = builder
				.with(SmallClient_.postCode, SearchOperations.CONTAINS, "352")
				.with(SmallClient_.city, SearchOperations.CONTAINS, "ennes").build();
		List<SmallClient> results = clientDao.findAll(spec);

		// Assert
		assertThat(results).usingRecursiveFieldByFieldElementComparator()
				.containsOnly(jeannetteNoisette35200);
	}

	@Test
	public void findWithService() {
		// Arrange
		SmallClientSearchDTO dto = new SmallClientSearchDTO();
		dto.setPostCode("352");

		// Act
		Specification<SmallClient> spec = searchService.convert(dto);
		List<SmallClient> results = clientDao.findAll(spec);

		// Assert
		assertThat(results).usingRecursiveFieldByFieldElementComparator()
				.containsOnly(jeannetteNoisette35200);
	}
}
