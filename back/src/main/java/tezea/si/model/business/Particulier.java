package tezea.si.model.business;

import javax.persistence.Entity;

@Entity
public class Particulier extends Client{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2675066872546116276L;
	private String nom;
	private String prenom;
	private String civilite;
	
	public Particulier() {}

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

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

}