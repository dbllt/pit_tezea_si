package tezea.si.model.dto;

public class SmallRequestSearchDTO {

	private String description;
	private SmallClientSearchDTO client;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SmallClientSearchDTO getClient() {
		return client;
	}

	public void setClient(SmallClientSearchDTO client) {
		this.client = client;
	}

}
