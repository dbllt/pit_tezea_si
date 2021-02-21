package tezea.si;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import tezea.si.dao.ClientDAO;
import tezea.si.model.business.Client;
import tezea.si.model.business.Client_;
import tezea.si.model.business.Particulier;
import tezea.si.model.dto.ClientSearchDTO;
import tezea.si.service.ClientSearchService;
import tezea.si.service.SpecificationBuilder;
import tezea.si.utils.search.SearchSpecification;
import tezea.si.utils.search.SearchCriteria;
import tezea.si.utils.search.SearchOperations;

@SpringBootTest
public class ClientSearchTests {

	@Autowired
	private ClientDAO clientDao;

	@Autowired
	private SpecificationBuilder<Client> builder;

	@Autowired
	private ClientSearchService searchService;

	private Particulier jeanNoix35700;
	private Particulier jeannetteNoisette35200;
	private Particulier liseOlli53500;

	@BeforeEach
	public void init() {
		Particulier particulier = new Particulier();
		particulier.setNom("Noix");
		particulier.setPrenom("Jean");
		particulier.setCodePostal("35700");
		particulier.setVille("rennes");
		jeanNoix35700 = clientDao.save(particulier);
		Particulier particulier2 = new Particulier();
		particulier2.setNom("Noisette");
		particulier2.setPrenom("Jeannette");
		particulier2.setCodePostal("35200");
		particulier2.setVille("rennes");
		jeannetteNoisette35200 = clientDao.save(particulier2);
		Particulier particulier3 = new Particulier();
		particulier3.setNom("Olli");
		particulier3.setPrenom("Lise");
		particulier3.setCodePostal("53500");
		particulier3.setVille("ailleurs");
		liseOlli53500 = clientDao.save(particulier3);
	}

	@AfterEach
	public void destroy() {
		clientDao.deleteAll();
	}

	@Test
	public void findByPostCodeContains() {
		// Arrange
		SearchSpecification<Client> spec = new SearchSpecification<Client>(
				new SearchCriteria<Client>(Client_.codePostal, SearchOperations.CONTAINS,
						"7"));

		// Act
		List<Client> results = clientDao.findAll(spec);

		// Assert
		assertThat(results).usingRecursiveFieldByFieldElementComparator()
				.containsOnly(jeanNoix35700);
	}

	@Test
	public void findByPostCodeStartsWith() {
		// Arrange
		SearchSpecification<Client> spec = new SearchSpecification<Client>(
				new SearchCriteria<Client>(Client_.codePostal,
						SearchOperations.STARTSWITH, "35"));

		// Act
		List<Client> results = clientDao.findAll(spec);

		// Assert
		assertThat(results).usingRecursiveFieldByFieldElementComparator()
				.containsOnly(jeanNoix35700, jeannetteNoisette35200);
	}

	@Test
	public void findEmptyByPostCodeContains() {
		// Arrange
		SearchSpecification<Client> spec = new SearchSpecification<Client>(
				new SearchCriteria<Client>(Client_.codePostal, SearchOperations.CONTAINS,
						"5"));

		// Act
		List<Client> results = clientDao.findAll(spec);

		// Assert
		assertThat(results).usingRecursiveFieldByFieldElementComparator()
				.containsOnly(jeanNoix35700, jeannetteNoisette35200, liseOlli53500);
	}

	@Test
	public void findEmptyByPostCodeEquals() {
		// Arrange
		SearchSpecification<Client> spec = new SearchSpecification<Client>(
				new SearchCriteria<Client>(Client_.codePostal, SearchOperations.EQUALS,
						"700"));

		// Act
		List<Client> results = clientDao.findAll(spec);

		// Assert
		assertThat(results).usingRecursiveFieldByFieldElementComparator().isEmpty();
	}

	@Test
	public void findByPostCodeEquals() {
		// Arrange
		SearchSpecification<Client> spec = new SearchSpecification<Client>(
				new SearchCriteria<Client>(Client_.codePostal, SearchOperations.EQUALS,
						"35700"));

		// Act
		List<Client> results = clientDao.findAll(spec);

		// Assert
		assertThat(results).usingRecursiveFieldByFieldElementComparator()
				.containsOnly(jeanNoix35700);
	}

	@Test
	public void findWithBuiltSpec() {
		// Act
		Specification<Client> spec = builder
				.with(Client_.codePostal, SearchOperations.CONTAINS, "352")
				.with(Client_.ville, SearchOperations.CONTAINS, "ennes").build();
		List<Client> results = clientDao.findAll(spec);

		// Assert
		assertThat(results).usingRecursiveFieldByFieldElementComparator()
				.containsOnly(jeannetteNoisette35200);
	}

	@Test
	public void findWithService() {
		// Arrange
		ClientSearchDTO dto = new ClientSearchDTO();
		dto.setAddress("352");

		// Act
		Specification<Client> spec = searchService.convert(dto);
		List<Client> results = clientDao.findAll(spec);

		// Assert
		assertThat(results).usingRecursiveFieldByFieldElementComparator()
				.containsOnly(jeannetteNoisette35200);
	}
}
