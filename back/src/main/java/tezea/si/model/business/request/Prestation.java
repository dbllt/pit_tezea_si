package tezea.si.model.business.request;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import tezea.si.model.business.RequestEmployee;

@Entity
public class Prestation {
    private long id;
    private Request request;
    private RequestEmployee employee;
    private Date date;
    private String details;
    private SatisfactionLevel satisfactionLevel;
    
    @Id
    @GeneratedValue
	public long getId() {
		return id;
	}
    @OneToOne(fetch = FetchType.LAZY)
	public Request getRequest() {
		return request;
	}
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_employee", insertable = false, updatable = false)
	public RequestEmployee getEmployee() {
		return employee;
	}
	public Date getDate() {
		return date;
	}
	public String getDetails() {
		return details;
	}
	@Enumerated(EnumType.STRING)
	public SatisfactionLevel getSatisfactionLevel() {
		return satisfactionLevel;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	public void setEmployee(RequestEmployee employee) {
		this.employee = employee;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public void setSatisfactionLevel(SatisfactionLevel satisfactionLevel) {
		this.satisfactionLevel = satisfactionLevel;
	}
}
