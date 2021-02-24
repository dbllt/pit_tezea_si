package tezea.si.model.dto;

import tezea.si.model.business.Site;

public class SmallRequestSearchDTO {

	private String description;
	private SmallClientSearchDTO client;
	private Site site;

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

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

}
