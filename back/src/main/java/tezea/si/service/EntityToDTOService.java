package tezea.si.service;

import org.springframework.stereotype.Component;

import tezea.si.model.SmallClientDTO;
import tezea.si.model.SmallEstimationDTO;
import tezea.si.model.SmallRequestDTO;
import tezea.si.model.SmallUserDTO;
import tezea.si.model.business.SmallClient;
import tezea.si.model.business.UserTezea;
import tezea.si.model.business.request.SmallEstimation;
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
		result.setInternalInfo(request.getInternalInfo());

		result.setClient(convertToDTO(request.getClient()));
		result.setResponsable(convertToDTO(request.getResponsable()));
		result.setLastUpdatedBy(convertToDTO(request.getLastUpdatedBy()));
		result.setClosedBy(convertToDTO(request.getClosedBy()));
		result.setLastUpdatedBy(convertToDTO(request.getLastUpdatedBy()));
		result.setEstimation(convertToDTO(request.getEstimation()));

		return result;
	}

	private SmallEstimationDTO convertToDTO(SmallEstimation estimation) {
		if (estimation == null) {
			return null;
		}
		SmallEstimationDTO result = new SmallEstimationDTO();
		result.setId(estimation.getId());
		result.setEstimationResponsable(convertToDTO(estimation.getEstimationResponsable()));
		result.setExpectedDuration(estimation.getExpectedDuration());
		result.setNumberEmployeesNeeded(estimation.getNumberEmployeesNeeded());
		result.setOtherTools(estimation.getOtherTools());
		result.setToolsNeeded(estimation.getToolsNeeded());
		result.setVehiclesNeeded(estimation.getVehiclesNeeded());
		return result;
	}

	private SmallClientDTO convertToDTO(SmallClient client) {
		if (client == null) {
			return null;
		}
		SmallClientDTO result = new SmallClientDTO();
		result.setId(client.getId());
		result.setEmail(client.getEmail());
		result.setPhoneNumber(client.getPhoneNumber());
		result.setPhoneNumber2(client.getPhoneNumber2());
		result.setAddress(client.getAddress());
		result.setPostCode(client.getPostCode());
		result.setCity(client.getCity());
		result.setCompanyName(client.getCompanyName());
		result.setLastName(client.getLastName());
		result.setFirstName(client.getFirstName());
		result.setHonorificTitle(client.getHonorificTitle());
		result.setType(client.getType());
		result.setSiret(client.getSiret());
		return result;
	}

	private SmallUserDTO convertToDTO(UserTezea user) {
		if (user == null) {
			return null;
		}
		SmallUserDTO result = new SmallUserDTO();
		result.setId(user.getId());
		result.setUsername(user.getUsername());
		return result;
	}
}
