package tezea.si;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;

import tezea.si.dao.ClientDAO;
import tezea.si.model.business.Client;
import tezea.si.model.business.Particulier;
import tezea.si.service.ClientSpecificationBuilder;
import tezea.si.utils.search.ClientSpecification;
import tezea.si.utils.search.SearchCriteria;

@DataJpaTest
public class ClientSearchTests {

	@Autowired
	private ClientDAO clientDao;

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

	@Test
	public void findByPostCodeContains() throws Exception {
		// Arrange
		ClientSpecification spec = new ClientSpecification(
				new SearchCriteria("codePostal", "contains", "7"));

		// Act
		List<Client> results = clientDao.findAll(spec);

		// Assert
		assertThat(results).containsOnly(jeanNoix35700);
	}
	
	@Test
	public void findByPostCodeStartsWith() throws Exception {
		// Arrange
		ClientSpecification spec = new ClientSpecification(
				new SearchCriteria("codePostal", "startswith", "35"));

		// Act
		List<Client> results = clientDao.findAll(spec);

		// Assert
		assertThat(results).containsOnly(jeanNoix35700, jeannetteNoisette35200);
	}
	
	@Test
	public void findEmptyByPostCodeContains() throws Exception {
		// Arrange
		ClientSpecification spec = new ClientSpecification(
				new SearchCriteria("codePostal", "contains", "5"));

		// Act
		List<Client> results = clientDao.findAll(spec);

		// Assert
		assertThat(results).containsOnly(jeanNoix35700, jeannetteNoisette35200, liseOlli53500);
	}
	
	@Test
	public void findEmptyByPostCodeEquals() throws Exception {
		// Arrange
		ClientSpecification spec = new ClientSpecification(
				new SearchCriteria("codePostal", "equals", "700"));

		// Act
		List<Client> results = clientDao.findAll(spec);

		// Assert
		assertThat(results).isEmpty();
	}
	
	@Test
	public void findByPostCodeEquals() throws Exception {
		// Arrange
		ClientSpecification spec = new ClientSpecification(
				new SearchCriteria("codePostal", "equals", "35700"));

		// Act
		List<Client> results = clientDao.findAll(spec);

		// Assert
		assertThat(results).containsOnly(jeanNoix35700);
	}

	@Test
	public void findWithBuiltSpec() {
		// Arrange
		ClientSpecificationBuilder builder = new ClientSpecificationBuilder();

		// Act
		Specification<Client> spec = builder.with("codePostal", "contains", "352")
				.with("ville", "contains", "ennes").build();
		List<Client> results = clientDao.findAll(spec);

		// Assert
		assertThat(results).containsOnly(jeannetteNoisette35200);
	}
}
