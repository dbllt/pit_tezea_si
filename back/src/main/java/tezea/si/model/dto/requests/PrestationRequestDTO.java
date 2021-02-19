package tezea.si.model.dto.requests;

import tezea.si.model.business.request.PrestationRequest;
import tezea.si.model.business.request.Request;

import java.io.Serializable;

public class PrestationRequestDTO extends RequestDTO implements Serializable {
    private EstimationDTO estimation;
    private PrestationDTO prestation;

    public PrestationRequestDTO(Request copy){
        super(copy);
        assert(copy instanceof PrestationRequest);

        PrestationRequest r = (PrestationRequest) copy;
        this.estimation = new EstimationDTO(r.getEstimation());
        this.prestation = new PrestationDTO(r.getPrestation());
    }

    public EstimationDTO getEstimation() {
        return estimation;
    }

    public void setEstimation(EstimationDTO estimation) {
        this.estimation = estimation;
    }
}
