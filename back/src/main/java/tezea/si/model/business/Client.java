package tezea.si.model.business;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public abstract class Client implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6515822297439920891L;
	protected long id;
	protected String email;
	protected String phone;
	protected Date addDate;
	protected String adress;
	protected String codePostal;
	protected String city;
	
	public Client() {}
	
	public Client(String email, String tel, String adresse, String codePostal, String ville) {
		this.email = email;
		this.phone = tel;
		this.adress = adresse;
		this.codePostal = codePostal;
		this.city = ville;
		Calendar today = Calendar.getInstance();
		this.addDate = today.getTime();
	}
	
	@Id
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String telephone) {
		this.phone = telephone;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date dateAjout) {
		this.addDate = dateAjout;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adresse) {
		this.adress = adresse;
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

	public void updateFrom(Client c) {
		if(c.adress != null) {
			this.adress = c.adress;
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
		
		if(c.phone != null) {
			this.phone = c.phone;
		}
		
		if(c.city != null) {
			this.city = c.city;
		}
		
	}
	
	
	
}
