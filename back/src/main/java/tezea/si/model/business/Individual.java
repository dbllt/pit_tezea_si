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
	private String lastname;
	private String firstname;
	private HonorificTitle honorificTitle;
	
	public Individual() {}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Enumerated(EnumType.STRING)
	public HonorificTitle getHonorificTitle() {
		return honorificTitle;
	}

	public void setHonorificTitle(HonorificTitle honorificTitle) {
		this.honorificTitle = honorificTitle;
	}
	
	public void updateFrom(Individual c) {
		super.updateFrom(c);
		
		if(c.firstname != null) {
			this.firstname = c.firstname;
		}
		if(c.lastname != null) {
			this.lastname = c.lastname;
		}
		if(c.honorificTitle != null) {
			this.honorificTitle = c.honorificTitle;
		}
		
	}

}