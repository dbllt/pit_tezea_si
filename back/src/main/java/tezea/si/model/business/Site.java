package tezea.si.model.business;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Site implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5796815850717651118L;
	private long id;
	private String nom;
	private UserTezea responsable;
	private String telephone;
	private String description;
	
	
	public Site() {}
	
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}

	@ManyToOne
	public UserTezea getResponsable() {
		return responsable;
	}


	public void setResponsable(UserTezea responsable) {
		this.responsable = responsable;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	

}
