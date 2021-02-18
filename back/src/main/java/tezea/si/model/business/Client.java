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
import javax.persistence.Transient;

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

	private String clientType;
	@Column(name = "client_type", insertable = false, updatable = false)
	public String getDiscriminator() {
		return clientType;
	}
	protected void setDiscriminator(String type) {
		this.clientType = type;
	}
	
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

	public void updateFrom(Client c) {
		if(c.adresse != null) {
			this.adresse = c.adresse;
		}
		
		if(c.codePostal != null) {
			this.codePostal = c.codePostal;
		}
		
		if(c.dateAjout != null) {
			this.dateAjout = c.dateAjout;
		}
		
		if(c.email != null) {
			this.email = c.email;
		}
		
		if(c.telephone != null) {
			this.telephone = c.telephone;
		}
		
		if(c.ville != null) {
			this.ville = c.ville;
		}
		
	}
}
