package tezea.si.model.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SmallClient {
	private Long id;
	private ClientType type;
	private String email;
	private String phoneNumber;
	private String phoneNumber2;
	private String address;
	private String postCode;
	private String city;
	private String companyName;
	private String siret;
	private String lastName;
	private String firstName;
	private HonorificTitle honorificTitle;

	public void updateFrom(SmallClient other) {
		this.type = other.type == null ? this.type : other.type;
		this.email = other.email == null ? this.email : other.email;
		this.phoneNumber = other.phoneNumber == null ? this.phoneNumber : other.phoneNumber;
		this.phoneNumber2 = other.phoneNumber2 == null ? this.phoneNumber2 : other.phoneNumber2;
		this.address = other.address == null ? this.address : other.address;
		this.postCode = other.postCode == null ? this.postCode : other.postCode;
		this.city = other.city == null ? this.city : other.city;
		this.companyName = other.companyName == null ? this.companyName : other.companyName;
		this.siret = other.siret == null ? this.siret : other.siret;
		this.lastName = other.lastName == null ? this.lastName : other.lastName;
		this.firstName = other.firstName == null ? this.firstName : other.firstName;
		this.honorificTitle = other.honorificTitle == null ? this.honorificTitle : other.honorificTitle;
    }
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public HonorificTitle getHonorificTitle() {
		return honorificTitle;
	}

	public void setHonorificTitle(HonorificTitle honorificTitle) {
		this.honorificTitle = honorificTitle;
	}

	public ClientType getType() {
		return type;
	}

	public void setType(ClientType type) {
		this.type = type;
	}

	public String getSiret() {
		return siret;
	}

	public void setSiret(String siret) {
		this.siret = siret;
	}

	public String getPhoneNumber2() {
		return phoneNumber2;
	}

	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}

}
