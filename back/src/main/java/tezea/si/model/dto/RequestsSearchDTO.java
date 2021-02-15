package tezea.si.model.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RequestsSearchDTO {

	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate startDate;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate endDate;
	private List<Urgency> urgency;
	private List<Site> site;
	private List<Service> service;
	private ClientSearchDTO client;
	private PaginationDTO pagination;

	public enum Urgency {
		LOW, NORMAL, AVERAGE, HIGH, VERY_HIGH
	}

	public enum Site {
		MENUISERIE, NETTOYAGE
	}

	public enum Service {
		SERVICE, DONATION, SERVICE_DONATION, PLASMA, INFORMATION
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

	public List<Urgency> getUrgency() {
		return urgency;
	}

	public void setUrgency(List<Urgency> urgency) {
		this.urgency = urgency;
	}

	public List<Site> getSite() {
		return site;
	}

	public void setSite(List<Site> site) {
		this.site = site;
	}

	public List<Service> getService() {
		return service;
	}

	public void setService(List<Service> service) {
		this.service = service;
	}

	public ClientSearchDTO getClient() {
		return client;
	}

	public void setClient(ClientSearchDTO client) {
		this.client = client;
	}

	public PaginationDTO getPagination() {
		return pagination;
	}

	public void setPagination(PaginationDTO pagination) {
		this.pagination = pagination;
	}

}
