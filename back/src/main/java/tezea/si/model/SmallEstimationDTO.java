package tezea.si.model;

import java.util.List;

import tezea.si.model.business.request.TimeUnit;
import tezea.si.model.business.request.Tool;
import tezea.si.model.business.request.Vehicle;

public class SmallEstimationDTO {
	private long id;
	private SmallUserDTO estimationResponsable;
	private int numberEmployeesNeeded;
	private List<Tool> toolsNeeded;
	private String otherTools;
	private List<Vehicle> vehiclesNeeded;
	private int expectedDuration;
	private TimeUnit expectedDurationUnit;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public SmallUserDTO getEstimationResponsable() {
		return estimationResponsable;
	}

	public void setEstimationResponsable(SmallUserDTO estimationResponsable) {
		this.estimationResponsable = estimationResponsable;
	}

	public int getNumberEmployeesNeeded() {
		return numberEmployeesNeeded;
	}

	public void setNumberEmployeesNeeded(int numberEmployeesNeeded) {
		this.numberEmployeesNeeded = numberEmployeesNeeded;
	}

	public List<Tool> getToolsNeeded() {
		return toolsNeeded;
	}

	public void setToolsNeeded(List<Tool> toolsNeeded) {
		this.toolsNeeded = toolsNeeded;
	}

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

	public String getOtherTools() {
		return otherTools;
	}

	public void setOtherTools(String otherTools) {
		this.otherTools = otherTools;
	}

}
