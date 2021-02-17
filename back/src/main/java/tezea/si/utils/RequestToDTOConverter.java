package tezea.si.utils;

import tezea.si.model.business.Client;
import tezea.si.model.business.Entreprise;
import tezea.si.model.business.Particulier;
import tezea.si.model.business.request.*;
import tezea.si.model.dto.ClientSearchDTO;
import tezea.si.model.dto.EnterpriseClientSearchDTO;
import tezea.si.model.dto.ParticulierClientSearchDTO;
import tezea.si.model.dto.requests.*;
import tezea.si.model.business.request.InformationRequest;

public class RequestToDTOConverter {
    public static RequestDTO pack(Request c) throws RuntimeException {
        if (c instanceof PrestationRequest) {
            return new PrestationRequestDTO(c);
        } else if (c instanceof PrestationAndDonationRequest) {
            return new PrestationAndDonationRequestDTO(c);
        }else if (c instanceof DonationRequest) {
            return new DonationRequestDTO(c);
        } else if (c instanceof PlasmaRequest) {
            return new PlasmaRequestDTO(c);
        }else if (c instanceof InformationRequest) {
            return new InformationRequestDTO(c);
        }else {
            throw new RuntimeException("Undefined subtype of Request");
        }
    }
}
