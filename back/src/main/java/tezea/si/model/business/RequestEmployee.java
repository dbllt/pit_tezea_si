package tezea.si.model.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RequestEmployee {
    private long id;
    private HonorificTitle honorificTitle;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    
    @Id
    @Column(name="id_employee")
    @GeneratedValue
	public long getId() {
		return id;
	}
    @Enumerated(EnumType.STRING)
	public HonorificTitle getHonorificTitle() {
		return honorificTitle;
	}
	public String getFirstname() {
		return firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public String getPhone() {
		return phone;
	}
	public String getEmail() {
		return email;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setHonorificTitle(HonorificTitle honorificTitle) {
		this.honorificTitle = honorificTitle;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setEmail(String email) {
		this.email = email;
	}

    
}
