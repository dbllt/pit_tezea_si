package tezea.si.model.business.request;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import tezea.si.model.business.Site;
import tezea.si.model.business.SmallClient;
import tezea.si.model.business.UserTezea;
import tezea.si.utils.StringListConverter;

@Entity
public class SmallRequest {
	private long id;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate date;
	private Site site;
	private UserTezea responsable;
	private SmallClient client;
	private Priority priority;
	private String description;
	private RequestStatus status;
	private UserTezea closedBy;
	private String accessDetails;
	private int repetitionTime;
	private TimeUnit repetitionUnit;
	private Service type;

	private int amountWood;
	private double amountDonated;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate appointmentPlasmaDate;
	private SmallEstimation estimation;

	private SatisfactionLevel satisfactionLevel;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate lastUpdated;
	private UserTezea lastUpdatedBy;

	private String internalInfo;

	private List<String> photos;

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "photos")
	@Convert(converter = StringListConverter.class)
	public List<String> getPhotos() {
		return photos;
	}

	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	@OneToOne
	public UserTezea getResponsable() {
		return responsable;
	}

	public void setResponsable(UserTezea responsable) {
		this.responsable = responsable;
	}

	@OneToOne
	public SmallClient getClient() {
		return client;
	}

	public void setClient(SmallClient client) {
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

	@OneToOne
	public UserTezea getClosedBy() {
		return closedBy;
	}

	public void setClosedBy(UserTezea closedBy) {
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

	@OneToOne
	public SmallEstimation getEstimation() {
		return estimation;
	}

	public void setEstimation(SmallEstimation estimation) {
		this.estimation = estimation;
	}

	@OneToOne
	public UserTezea getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(UserTezea lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Service getType() {
		return type;
	}

	public void setType(Service type) {
		this.type = type;
	}

	public String getInternalInfo() {
		return internalInfo;
	}

	public void setInternalInfo(String internalInfo) {
		this.internalInfo = internalInfo;
	}

}
