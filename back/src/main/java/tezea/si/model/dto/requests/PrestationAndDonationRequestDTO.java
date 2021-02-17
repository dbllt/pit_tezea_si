package tezea.si.model.dto.requests;

import tezea.si.model.business.request.PrestationAndDonationRequest;
import tezea.si.model.business.request.Request;

import java.io.Serializable;

public class PrestationAndDonationRequestDTO extends RequestDTO implements Serializable {
    private int amount;
    private EstimationDTO estimation;
    private PrestationDTO prestation;

    public PrestationAndDonationRequestDTO(Request copy){
        super(copy);
        assert(copy instanceof PrestationAndDonationRequest);
        PrestationAndDonationRequest r = (PrestationAndDonationRequest) copy;

        this.amount = r.getAmount();
        this.estimation = new EstimationDTO(r.getEstimation());
        this.prestation = new PrestationDTO(r.getPrestation());
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public EstimationDTO getEstimation() {
        return estimation;
    }

    public void setEstimation(EstimationDTO estimation) {
        this.estimation = estimation;
    }

    public PrestationDTO getPrestation() {
        return prestation;
    }

    public void setPrestation(PrestationDTO prestation) {
        this.prestation = prestation;
    }
}
