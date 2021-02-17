package tezea.si.model.business;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import tezea.si.model.business.request.Request;

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
	private List<Request> requests;
	
	public Site() {}
	
	
	@Id
	@Column(name="id_site", nullable=false)
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

	@ManyToOne()
	//@JoinColumn(name="id_user", insertable=false, updatable=false)
	public UserTezea getResponsable() {
		return responsable;
	}


	public void setResponsable(UserTezea responsable) {
		this.responsable = responsable;
	}


	public String getTelephone() {
		return telephone;
	}
	
	@OneToMany(mappedBy="site", fetch = FetchType.LAZY)
	public List<Request> getRequests() {
		return requests;
	}
	
	public void setRequests(List<Request> requests) {
		this.requests = requests;
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
