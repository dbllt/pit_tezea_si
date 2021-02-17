package tezea.si.model.business;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Entreprise")
public class Enterprise extends Client{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2675066872546116276L;
	private String nom;
	
	public Enterprise() {}
	
	public Enterprise(String email, String tel, String adresse, String codePostal, String ville, String nom) {
		super(email,tel,adresse,  codePostal, ville);
		this.nom = nom;
	}


	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
