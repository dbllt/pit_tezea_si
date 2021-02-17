package tezea.si.model.business;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import tezea.si.model.business.request.Request;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="client_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6515822297439920891L;
	protected long id;
	protected String email;
	protected String telephone;
	protected Date dateAjout;
	protected String adresse;
	protected String codePostal;
	protected String ville;
	protected List<Request> requests;

	public Client() {
	}

	public Client(String email, String tel, String adresse, String codePostal, String ville) {
		this.email = email;
		this.telephone = tel;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;

		this.dateAjout = new Date(1000);
	}

	@Id
	@Column(name="id_client", nullable=false)
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getDateAjout() {
		return dateAjout;
	}

	public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@OneToMany(mappedBy = "client", orphanRemoval = true, fetch = FetchType.LAZY)
	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

}
