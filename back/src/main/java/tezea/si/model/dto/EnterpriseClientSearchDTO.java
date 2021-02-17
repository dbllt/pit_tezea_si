package tezea.si.model.dto;

import java.io.Serializable;

import tezea.si.model.business.Client;
import tezea.si.model.business.Enterprise;

public class EnterpriseClientSearchDTO extends ClientSearchDTO implements Serializable {
	private String name;
	
	public EnterpriseClientSearchDTO() {
		super();
	}
	
	public EnterpriseClientSearchDTO(Client copy) {		
		super(copy);
		assert(copy instanceof Enterprise);
		Enterprise e = (Enterprise)copy;
		setName(e.getNom());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
