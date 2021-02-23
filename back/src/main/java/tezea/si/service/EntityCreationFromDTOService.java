package tezea.si.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tezea.si.dao.SmallClientDAO;
import tezea.si.dao.SmallEstimationDAO;
import tezea.si.dao.SmallRequestDAO;
import tezea.si.dao.UserTezeaDAO;
import tezea.si.model.business.SmallClient;
import tezea.si.model.business.UserTezea;
import tezea.si.model.business.request.SmallEstimation;
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
	SmallEstimationDAO estimationDao;

	public SmallRequest convertToEntity(SmallRequest request) {
		request.setLastUpdated(LocalDate.now());
		request.setClient(convertToEntity(request.getClient()));
		request.setResponsable(convertToEntity(request.getResponsable()));
		request.setClosedBy(convertToEntity(request.getClosedBy()));
		request.setLastUpdatedBy(convertToEntity(request.getLastUpdatedBy()));
		request.setEstimation(convertToEntity(request.getEstimation()));

		SmallRequest result = requestDao.save(request);
		return result;
	}

	private SmallEstimation convertToEntity(SmallEstimation estimation) {
		if (estimation == null) {
			return null;
		}
		estimation.setEstimationResponsable(
				convertToEntity(estimation.getEstimationResponsable()));
		SmallEstimation result = estimationDao.save(estimation);
		return result;
	}

	private UserTezea convertToEntity(UserTezea responsable) {
		if (responsable == null) {
			return null;
		}
		String username = responsable.getUsername();
		if (userDao.checkForExistanceUsername(username)) {
			return userDao.getUserByName(username);
		}
		throw new RuntimeException("Could not find user " + username);
	}

	private SmallClient convertToEntity(SmallClient client) {
		if (client == null) {
			return null;
		}
		SmallClient result = clientDao.save(client);
		return result;
	}

}
