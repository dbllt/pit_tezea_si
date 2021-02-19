package tezea.si.model.dto.requests;

import tezea.si.model.business.request.Request;

import java.io.Serializable;

public class InformationRequestDTO extends RequestDTO implements Serializable {

    public InformationRequestDTO(Request copy){
        super(copy);
    }
}
