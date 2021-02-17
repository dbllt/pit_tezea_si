package tezea.si.model.dto;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Transient;

import tezea.si.model.business.Client;

public abstract class ClientSearchDTO implements Serializable {
	private long id;
	private String firstName;
	private String lastName;
	private String tel;
	private String email;
	private String address;
	private String codePostal;
	private String city;
	private Date date;
	private String inherits;
	
	public ClientSearchDTO() {
		
	}
	
	public ClientSearchDTO(Client copy) {
		inherits = (copy.getDiscriminator());
		setId(copy.getId());
		setEmail(copy.getEmail());
		setCodePostal(copy.getCodePostal());
		setDate(copy.getDateAjout());
		setTel(copy.getTelephone());
		setCity(copy.getVille());
		setAddress(copy.getAdresse());
		//copy.getRequests()
	}
	
	public String getDiscriminator() {
		return inherits;
	}
	
	public long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTel() {
		return tel;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public String getCity() {
		return city;
	}

	public Date getDate() {
		return date;
	}

	public void setId(Long long1) {
		this.id = long1;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


}
