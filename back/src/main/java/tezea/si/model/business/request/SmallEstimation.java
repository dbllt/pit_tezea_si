package tezea.si.model.business.request;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import tezea.si.model.business.UserTezea;

@Entity
public class SmallEstimation {
	private long id;
	private UserTezea estimationResponsable;
	private int numberEmployeesNeeded;
	private List<Tool> toolsNeeded;
	private String otherTools;
	private List<Vehicle> vehiclesNeeded;
	private int expectedDuration;

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

	public int getNumberEmployeesNeeded() {
		return numberEmployeesNeeded;
	}

	public void setNumberEmployeesNeeded(int numberEmployeesNeeded) {
		this.numberEmployeesNeeded = numberEmployeesNeeded;
	}

	@ElementCollection
	public List<Tool> getToolsNeeded() {
		return toolsNeeded;
	}

	public void setToolsNeeded(List<Tool> toolsNeeded) {
		this.toolsNeeded = toolsNeeded;
	}

	@ElementCollection
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

	public String getOtherTools() {
		return otherTools;
	}

	public void setOtherTools(String otherTools) {
		this.otherTools = otherTools;
	}

}