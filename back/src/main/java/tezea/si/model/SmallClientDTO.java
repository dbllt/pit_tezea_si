package tezea.si.model;

import tezea.si.model.business.HonorificTitle;

public class SmallClientDTO {

	private long id;
	private String email;
	private String phoneNumber;
	private String address;
	private String postCode;
	private String city;
	private String companyName;
	private String lastName;
	private String firstName;
	private HonorificTitle honorificTitle;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

}
