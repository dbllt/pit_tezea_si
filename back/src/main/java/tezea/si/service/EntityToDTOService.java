package tezea.si.service;

import org.springframework.stereotype.Component;

import tezea.si.model.SmallClientDTO;
import tezea.si.model.SmallRequestDTO;
import tezea.si.model.SmallUserDTO;
import tezea.si.model.business.SmallClient;
import tezea.si.model.business.UserTezea;
import tezea.si.model.business.request.SmallRequest;

@Component
public class EntityToDTOService {

	public SmallRequestDTO convertToDTO(SmallRequest request) {
		SmallRequestDTO result = new SmallRequestDTO();
		result.setId(request.getId());
		result.setAccessDetails(request.getAccessDetails());
		result.setLastUpdated(request.getLastUpdated());
		result.setDate(request.getDate());
		result.setDescription(request.getDescription());
		result.setPriority(request.getPriority());
		result.setRepetitionTime(request.getRepetitionTime());
		result.setRepetitionUnit(request.getRepetitionUnit());
		result.setStatus(request.getStatus());
		result.setAmountDonated(request.getAmountDonated());
		result.setAmountWood(request.getAmountWood());
		result.setAppointmentPlasmaDate(request.getAppointmentPlasmaDate());
		result.setSatisfactionLevel(request.getSatisfactionLevel());
		result.setType(request.getType());
		result.setPhotos(request.getPhotos());
		result.setSite(request.getSite());

		result.setClient(convertToDTO(request.getClient()));
		result.setResponsable(convertToDTO(request.getResponsable()));
		result.setLastUpdatedBy(convertToDTO(request.getLastUpdatedBy()));
		result.setClosedBy(convertToDTO(request.getClosedBy()));
		result.setLastUpdatedBy(convertToDTO(request.getLastUpdatedBy()));

		return result;
	}

	private SmallClientDTO convertToDTO(SmallClient client) {
		if (client == null) {
			return null;
		}
		SmallClientDTO result = new SmallClientDTO();
		result.setLastName(client.getLastName());
		result.setAddress(client.getAddress());
		return result;
	}

	private SmallUserDTO convertToDTO(UserTezea user) {
		if (user == null) {
			return null;
		}
		SmallUserDTO result = new SmallUserDTO();
		result.setUsername(user.getUsername());
		return result;
	}
}
