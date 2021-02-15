package tezea.si.model.business;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import net.minidev.json.JSONObject;

@Entity
public abstract class Client implements Serializable{
	
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
	
	public Client() {}
	
	public Client(String email, String tel, String adresse, String codePostal, String ville) {
		this.email = email;
		this.telephone = tel;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		
		this.dateAjout = new Date(1000);
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
	
	
	public abstract JSONObject toJSON();
	
	
	
}
