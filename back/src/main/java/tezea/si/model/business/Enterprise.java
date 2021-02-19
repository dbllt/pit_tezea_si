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
	private String name;
	
	public Enterprise() {}
	
	public Enterprise(String email, String tel, String adresse, String codePostal, String ville, String nom) {
		super(email,tel,adresse,  codePostal, ville);
		this.name = nom;
	}


	public String getName() {
		return name;
	}

	public void setName(String nom) {
		this.name = nom;
	}
	
	
	public void updateFrom(Enterprise c) {
		super.updateFrom(c);
		if(c.name != null) {
			this.name = c.name;
		}
		
	}

}
