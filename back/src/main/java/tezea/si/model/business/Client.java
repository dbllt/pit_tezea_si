package tezea.si.model.business;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Calendar;

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
	protected String phoneNumber;
	protected Date addDate;
	protected String address;
	protected String codePostal;
	protected String city;
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

	public Client(String email, String tel, String adresse, String codePostal, String city) {
		this.email = email;
		this.phoneNumber = tel;
		this.address = adresse;
		this.codePostal = codePostal;
		this.city = city;
		this.addDate = new Date(1000);
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phone) {
		this.phoneNumber = phone;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getCity() {
		return city;
	}
	
	public void setCity(String ville) {
		this.city = ville;
	}

	@OneToMany(mappedBy = "client", orphanRemoval = true, fetch = FetchType.LAZY)
	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	public void updateFrom(Client c) {
		if(c.address != null) {
			this.address = c.address;
		}
		
		if(c.codePostal != null) {
			this.codePostal = c.codePostal;
		}
		
		if(c.addDate != null) {
			this.addDate = c.addDate;
		}
		
		if(c.email != null) {
			this.email = c.email;
		}
		
		if(c.phoneNumber != null) {
			this.phoneNumber = c.phoneNumber;
		}
		
		if(c.city != null) {
			this.city = c.city;
		}
		
	}
}
