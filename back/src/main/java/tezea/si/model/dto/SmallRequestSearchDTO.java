package tezea.si.model.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import tezea.si.model.business.Site;
import tezea.si.model.business.request.RequestStatus;
import tezea.si.model.business.request.Service;

public class SmallRequestSearchDTO {

	private String description;
	private SmallClientSearchDTO client;
	private Site site;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate startDate;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate endDate;
	private Service type;
	private RequestStatus status;

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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Service getType() {
		return type;
	}

	public void setType(Service type) {
		this.type = type;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

}
