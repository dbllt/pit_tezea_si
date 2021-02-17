package tezea.si.utils;

import tezea.si.model.business.Client;
import tezea.si.model.business.Enterprise;
import tezea.si.model.business.Individual;
import tezea.si.model.dto.ClientSearchDTO;
import tezea.si.model.dto.EnterpriseClientSearchDTO;
import tezea.si.model.dto.IndividualClientSearchDTO;

public class ClientToDTOConverter {
	public static ClientSearchDTO pack(Client c) throws RuntimeException {
		if (c instanceof Enterprise) {
			return new EnterpriseClientSearchDTO(c);
		} else if (c instanceof Individual) {
			return new IndividualClientSearchDTO(c);
		} else {
			return null;
			//throw new RuntimeException("Undefined subtype of client");
		}
	}
}
