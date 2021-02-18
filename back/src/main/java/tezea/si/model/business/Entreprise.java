package tezea.si.model.business;

import javax.persistence.Entity;

@Entity
public class Entreprise extends Client{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2675066872546116276L;
	private String name;
	
	public Entreprise() {}
	public Entreprise(String email, String tel, String adresse, String codePostal, String ville, String nom) {
		super(email,tel,adresse,  codePostal, ville);
		this.name = nom;
	}


	public String getNom() {
		return name;
	}

	public void setNom(String nom) {
		this.name = nom;
	}
	
	
	public void updateFrom(Entreprise c) {
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
		if(c.name != null) {
			this.name = c.name;
		}
		
	}

}
