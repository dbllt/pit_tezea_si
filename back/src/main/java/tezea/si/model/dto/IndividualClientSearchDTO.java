package tezea.si.model.dto;

import java.io.Serializable;

import tezea.si.model.business.Client;
import tezea.si.model.business.HonorificTitle;
import tezea.si.model.business.Individual;

public class IndividualClientSearchDTO extends ClientSearchDTO implements Serializable {
	private String nom;
	private String prenom;
	private HonorificTitle honorificTitle;
	
	public IndividualClientSearchDTO() {
		super();
	}
	
	public IndividualClientSearchDTO(Client copy) {		
		super(copy);
		assert(copy instanceof Individual);
		Individual p = (Individual)copy;
		setNom(p.getFirstname());
		setPrenom(p.getLastname());
		setHonorificTitle(p.getHonorificTitle());
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public HonorificTitle getHonorificTitle() {
		return honorificTitle;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setHonorificTitle(HonorificTitle honorificTitle) {
		this.honorificTitle = honorificTitle;
	}

	
}
