package tezea.si.model.business;

public class Particulier extends Client{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2675066872546116276L;
	private String name;
	private String surname;
	private String civility;
	
	public Particulier() {}

	
	
	public void updateFrom(Particulier c) {
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
		if(c.surname != null) {
			this.surname = c.surname;
		}
		if(c.civility != null) {
			this.civility = c.civility;
		}
		
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getSurname() {
		return surname;
	}



	public void setSurname(String surname) {
		this.surname = surname;
	}



	public String getCivility() {
		return civility;
	}



	public void setCivility(String civility) {
		this.civility = civility;
	}
	
	

}