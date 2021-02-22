package tezea.si.model.business.request;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import tezea.si.model.business.UserTezea;

@Entity
public class SmallEstimation {
	private long id;
	private UserTezea estimationResponsable;
	private Date date;
	private double amount;
	private int numberEmployeesNeeded;
	private List<Tool> toolsNeeded;
	private List<Vehicle> vehiclesNeeded;
	private int expectedDuration;
	private TimeUnit expectedDurationUnit;

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@OneToOne
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

	public int getNumberEmployeesNeeded() {
		return numberEmployeesNeeded;
	}

	public void setNumberEmployeesNeeded(int numberEmployeesNeeded) {
		this.numberEmployeesNeeded = numberEmployeesNeeded;
	}

	@ManyToMany
	public List<Tool> getToolsNeeded() {
		return toolsNeeded;
	}

	public void setToolsNeeded(List<Tool> toolsNeeded) {
		this.toolsNeeded = toolsNeeded;
	}

	@ManyToMany
	public List<Vehicle> getVehiclesNeeded() {
		return vehiclesNeeded;
	}

	public void setVehiclesNeeded(List<Vehicle> vehiclesNeeded) {
		this.vehiclesNeeded = vehiclesNeeded;
	}

	public int getExpectedDuration() {
		return expectedDuration;
	}

	public void setExpectedDuration(int expectedDuration) {
		this.expectedDuration = expectedDuration;
	}

	public TimeUnit getExpectedDurationUnit() {
		return expectedDurationUnit;
	}

	public void setExpectedDurationUnit(TimeUnit expectedDurationUnit) {
		this.expectedDurationUnit = expectedDurationUnit;
	}

}