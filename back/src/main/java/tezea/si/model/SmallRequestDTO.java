package tezea.si.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import tezea.si.model.business.request.Priority;
import tezea.si.model.business.request.RequestStatus;
import tezea.si.model.business.request.SatisfactionLevel;
import tezea.si.model.business.request.Service;
import tezea.si.model.business.request.TimeUnit;

public class SmallRequestDTO {
	private long id;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate date;
	private SmallSiteDTO site;
	private SmallUserDTO responsable;
	private SmallClientDTO client;
	private Priority priority;
	private String description;
	private RequestStatus status;
	private SmallUserDTO closedBy;
	private String accessDetails;
	private int repetitionTime;
	private TimeUnit repetitionUnit;
	private Service type;

	private int amountWood;
	private double amountDonated;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate appointmentPlasmaDate;
	private SmallEstimationDTO estimation;

	private SatisfactionLevel satisfactionLevel;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate lastUpdated;
	private SmallUserDTO lastUpdatedBy;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public SmallSiteDTO getSite() {
		return site;
	}

	public void setSite(SmallSiteDTO site) {
		this.site = site;
	}

	public SmallUserDTO getResponsable() {
		return responsable;
	}

	public void setResponsable(SmallUserDTO responsable) {
		this.responsable = responsable;
	}

	public SmallClientDTO getClient() {
		return client;
	}

	public void setClient(SmallClientDTO client) {
		this.client = client;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public SmallUserDTO getClosedBy() {
		return closedBy;
	}

	public void setClosedBy(SmallUserDTO closedBy) {
		this.closedBy = closedBy;
	}

	public String getAccessDetails() {
		return accessDetails;
	}

	public void setAccessDetails(String accessDetails) {
		this.accessDetails = accessDetails;
	}

	public int getRepetitionTime() {
		return repetitionTime;
	}

	public void setRepetitionTime(int repetitionTime) {
		this.repetitionTime = repetitionTime;
	}

	public TimeUnit getRepetitionUnit() {
		return repetitionUnit;
	}

	public void setRepetitionUnit(TimeUnit repetitionUnit) {
		this.repetitionUnit = repetitionUnit;
	}

	public int getAmountWood() {
		return amountWood;
	}

	public void setAmountWood(int amountWood) {
		this.amountWood = amountWood;
	}

	public double getAmountDonated() {
		return amountDonated;
	}

	public void setAmountDonated(double amountDonated) {
		this.amountDonated = amountDonated;
	}

	public LocalDate getAppointmentPlasmaDate() {
		return appointmentPlasmaDate;
	}

	public void setAppointmentPlasmaDate(LocalDate appointmentPlasmaDate) {
		this.appointmentPlasmaDate = appointmentPlasmaDate;
	}

	public SmallEstimationDTO getEstimation() {
		return estimation;
	}

	public void setEstimation(SmallEstimationDTO estimation) {
		this.estimation = estimation;
	}

	public SatisfactionLevel getSatisfactionLevel() {
		return satisfactionLevel;
	}

	public void setSatisfactionLevel(SatisfactionLevel satisfactionLevel) {
		this.satisfactionLevel = satisfactionLevel;
	}

	public LocalDate getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDate lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public SmallUserDTO getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(SmallUserDTO lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Service getType() {
		return type;
	}

	public void setType(Service type) {
		this.type = type;
	}

}
