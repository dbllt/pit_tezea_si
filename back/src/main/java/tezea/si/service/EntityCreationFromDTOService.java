package tezea.si.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tezea.si.dao.SiteDAO;
import tezea.si.dao.SmallClientDAO;
import tezea.si.dao.SmallRequestDAO;
import tezea.si.dao.UserTezeaDAO;
import tezea.si.model.SmallClientDTO;
import tezea.si.model.SmallRequestDTO;
import tezea.si.model.SmallSiteDTO;
import tezea.si.model.SmallUserDTO;
import tezea.si.model.business.Site;
import tezea.si.model.business.SmallClient;
import tezea.si.model.business.UserTezea;
import tezea.si.model.business.request.SmallRequest;

@Component
public class EntityCreationFromDTOService {

	@Autowired
	SmallRequestDAO requestDao;

	@Autowired
	SmallClientDAO clientDao;
	
	@Autowired
	UserTezeaDAO userDao;
	
	@Autowired
	SiteDAO siteDao;

	public SmallRequest convertToEntity(SmallRequest request) {
		SmallRequest result = new SmallRequest();
		result.setDate(request.getDate());
		result.setLastUpdated(LocalDate.now());
		result.setAccessDetails(request.getAccessDetails());
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

		result.setClient(convertToEntity(request.getClient()));
		result.setResponsable(convertToEntity(request.getResponsable()));
		result.setSite(convertToEntity(request.getSite()));
		result.setClosedBy(convertToEntity(request.getClosedBy()));
		result.setLastUpdatedBy(convertToEntity(request.getLastUpdatedBy()));

		requestDao.save(result);
		return result;
	}

	private Site convertToEntity(Site site) {
		if (site == null) {
			return null;
		}
		String name = site.getNom();
		if(siteDao.checkForExistanceName(name)) {
			return siteDao.getSiteByName(name);
		}
		throw new RuntimeException("Could not find site " + name);
	}

	private UserTezea convertToEntity(UserTezea responsable) {
		if (responsable == null) {
			return null;
		}
		String username = responsable.getUsername();
		if(userDao.checkForExistanceUsername(username)) {
			return userDao.getUserByName(username);
		}
		throw new RuntimeException("Could not find user " + username);
	}

	private SmallClient convertToEntity(SmallClient client) {
		if (client == null) {
			return null;
		}
		SmallClient result = new SmallClient();
		result.setLastName(client.getLastName());
		result.setAddress(client.getAddress());
		clientDao.save(result);
		return result;
	}


}
