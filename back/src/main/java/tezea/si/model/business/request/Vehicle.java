package tezea.si.model.business.request;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Vehicle {
	private String name;

	@Id
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
