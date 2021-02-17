package tezea.si.model.dto;

import java.io.Serializable;

import tezea.si.model.business.Client;
import tezea.si.model.business.Entreprise;

public class EnterpriseClientSearchDTO extends ClientSearchDTO implements Serializable {
	private String name;
	
	public EnterpriseClientSearchDTO() {
		super();
	}
	
	public EnterpriseClientSearchDTO(Client copy) {		
		super(copy);
		assert(copy instanceof Entreprise);
		Entreprise e = (Entreprise)copy;
		setName(e.getNom());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
