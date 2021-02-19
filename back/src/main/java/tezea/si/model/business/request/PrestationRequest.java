package tezea.si.model.business.request;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("Prestation")
public class PrestationRequest extends Request{
    private Estimation estimation;
    private Prestation prestation;
    
    @OneToOne(mappedBy = "request", cascade = CascadeType.REMOVE)
	public Estimation getEstimation() {
		return estimation;
	}

	@OneToOne(mappedBy = "request", cascade = CascadeType.REMOVE)
	public Prestation getPrestation() {
		return prestation;
	}
	
	protected void setEstimation(Estimation estimation) {
		this.estimation = estimation;
	}

	protected void setPrestation(Prestation prestation) {
		this.prestation = prestation;
	}
}
