package tezea.si.model.dto.requests;

import tezea.si.model.business.request.DonationRequest;
import tezea.si.model.business.request.Request;

import java.io.Serializable;

public class DonationRequestDTO extends RequestDTO implements Serializable {
    double amount;

    public DonationRequestDTO(Request copy){
        super(copy);
        assert(copy instanceof DonationRequest);

        DonationRequest r = (DonationRequest) copy;
        this.amount = r.getAmount();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
