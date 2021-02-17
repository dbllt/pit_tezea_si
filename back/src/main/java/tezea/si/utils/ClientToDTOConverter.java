package tezea.si.utils;

import tezea.si.model.business.Client;
import tezea.si.model.business.Entreprise;
import tezea.si.model.business.Particulier;
import tezea.si.model.dto.ClientSearchDTO;
import tezea.si.model.dto.EnterpriseClientSearchDTO;
import tezea.si.model.dto.ParticulierClientSearchDTO;

public class ClientToDTOConverter {
	public static ClientSearchDTO pack(Client c) throws RuntimeException {
		if (c instanceof Entreprise) {
			return new EnterpriseClientSearchDTO(c);
		} else if (c instanceof Particulier) {
			return new ParticulierClientSearchDTO(c);
		} else {
			throw new RuntimeException("Undefined subtype of client");
		}
	}
}
