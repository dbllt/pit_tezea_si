package tezea.si.model.business.request;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import tezea.si.model.business.UserTezea;

@Entity
public class Estimation {
    private long id;
    private Request request;
    //TODO : Utiliser idUserTezea plutôt que d'avoir l'objet complet ?
    private UserTezea estimationResponsable;
    private Date date;
    private double amount;
    private String materialEstimation;
    
    @Id
    @GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn
	//@JoinColumn(name="id_user", insertable=false, updatable=false)
	public UserTezea getEstimationResponsable() {
		return estimationResponsable;
	}
	public void setEstimationResponsable(UserTezea estimationResponsable) {
		this.estimationResponsable = estimationResponsable;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getMaterialEstimation() {
		return materialEstimation;
	}
	public void setMaterialEstimation(String materialEstimation) {
		this.materialEstimation = materialEstimation;
	}
    
    
}
