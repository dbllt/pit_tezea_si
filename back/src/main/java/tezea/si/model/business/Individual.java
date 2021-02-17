package tezea.si.model.business;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("Particulier")
public class Individual extends Client {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2675066872546116276L;
	private String nom;
	private String prenom;
	private HonorificTitle honorificTitle;
	
	public Individual() {}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Enumerated(EnumType.STRING)
	public HonorificTitle getHonorificTitle() {
		return honorificTitle;
	}

	public void setHonorificTitle(HonorificTitle honorificTitle) {
		this.honorificTitle = honorificTitle;
	}

}