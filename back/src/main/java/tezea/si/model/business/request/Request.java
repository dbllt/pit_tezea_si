package tezea.si.model.business.request;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import tezea.si.model.business.Client;
import tezea.si.model.business.Site;
import tezea.si.model.business.UserTezea;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "request_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Request {
	

	private long id;
	private Date date;
	private Site site;
	// TODO : Utiliser idUserTezea plutôt que d'avoir l'objet complet ?
	private UserTezea responsable;
	private Client client;
	private Priority priority;
	private String description;
	private RequestStatus status;
	// TODO : Utiliser idUserTezea plutôt que d'avoir l'objet complet ?
	private UserTezea closedBy;
	
	private String requestType;
	@Column(name = "request_type", insertable = false, updatable = false)
	public String getDiscriminator() {
		return requestType;
	}
	protected void setDiscriminator(String type) {
		this.requestType = type;
	}
	
	@Id
	@Column(name = "id_request", nullable = false)
	@GeneratedValue
	public long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn
	public Site getSite() {
		return site;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn
	// @JoinColumn(name="id_user", insertable=false, updatable=false)
	public UserTezea getResponsable() {
		return responsable;
	}

	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn
	public Client getClient() {
		return client;
	}

	@Enumerated(EnumType.STRING)
	public Priority getPriority() {
		return priority;
	}

	public String getDescription() {
		return description;
	}

	@Enumerated(EnumType.STRING)
	public RequestStatus getStatus() {
		return status;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	// @JoinColumn(name="id_user", insertable=false, updatable=false)
	public UserTezea getClosedBy() {
		return closedBy;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public void setResponsable(UserTezea responsable) {
		this.responsable = responsable;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public void setClosedBy(UserTezea closedBy) {
		this.closedBy = closedBy;
	}
}
