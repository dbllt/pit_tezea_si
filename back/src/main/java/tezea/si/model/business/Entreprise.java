package tezea.si.model.business;

import java.util.HashMap;

import javax.persistence.Entity;

import net.minidev.json.JSONObject;

@Entity
public class Entreprise extends Client{
	
	private String nom;
	
	public Entreprise() {}
	public Entreprise(String email, String tel, String adresse, String codePostal, String ville, String nom) {
		super(email,tel,adresse,  codePostal, ville);
		this.nom = nom;
	}

	@Override
	public JSONObject toJSON() {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("adresse", this.adresse);
		map.put("codePostal", this.codePostal);
		map.put("dateAjout", this.dateAjout.toString());
		map.put("email", this.email);
		map.put("telephone", this.telephone);
		map.put("ville", this.ville);
		map.put("id", String.valueOf(this.id));
		map.put("nom", String.valueOf(this.nom));
		
		return new JSONObject(map);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
