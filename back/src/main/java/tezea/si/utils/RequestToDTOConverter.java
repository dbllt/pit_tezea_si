package tezea.si.utils;

import tezea.si.model.business.request.DonationRequest;
import tezea.si.model.business.request.InformationRequest;
import tezea.si.model.business.request.PlasmaRequest;
import tezea.si.model.business.request.PrestationAndDonationRequest;
import tezea.si.model.business.request.PrestationRequest;
import tezea.si.model.business.request.Request;
import tezea.si.model.dto.requests.DonationRequestDTO;
import tezea.si.model.dto.requests.InformationRequestDTO;
import tezea.si.model.dto.requests.PlasmaRequestDTO;
import tezea.si.model.dto.requests.PrestationAndDonationRequestDTO;
import tezea.si.model.dto.requests.PrestationRequestDTO;
import tezea.si.model.dto.requests.RequestDTO;

public class RequestToDTOConverter {
	public static RequestDTO pack(Request c) throws RuntimeException {
		if (c instanceof PrestationRequest) {
			return new PrestationRequestDTO(c);
		} else if (c instanceof PrestationAndDonationRequest) {
			return new PrestationAndDonationRequestDTO(c);
		} else if (c instanceof DonationRequest) {
			return new DonationRequestDTO(c);
		} else if (c instanceof PlasmaRequest) {
			return new PlasmaRequestDTO(c);
		} else if (c instanceof InformationRequest) {
			return new InformationRequestDTO(c);
		} else {
			//Logger.getGlobal().info(c.getClass().getCanonicalName());
			//throw new RuntimeException("Undefined subtype of Request");
			return null;
		}
	}
}
