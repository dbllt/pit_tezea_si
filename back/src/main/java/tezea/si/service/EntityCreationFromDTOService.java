package tezea.si.service;

import java.time.LocalDate;
import java.util.Optional;

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

        request.setResponsable(convertToEntity(request.getResponsable()));
        request.setClosedBy(convertToEntity(request.getClosedBy()));
        request.setLastUpdatedBy(convertToEntity(request.getLastUpdatedBy()));

        request.setClient(convertToEntity(request.getClient()));
        request.setEstimation(convertToEntity(request.getEstimation()));

        return requestDao.save(request);
    }

    private SmallEstimation convertToEntity(SmallEstimation estimation) {
        if (estimation == null) {
            return new SmallEstimation();
        }

        if (estimation.getId() != null) {
            Optional<SmallEstimation> e = estimationDao.findById(estimation.getId());

            if (e.isPresent()) {
                e.get().updateFrom(estimation);
                estimation = e.get();
            } else {
                SmallEstimation sc = new SmallEstimation();
                sc.updateFrom(estimation);
                return sc;
            }

        }
        
        estimation.setEstimationResponsable(convertToEntity(estimation.getEstimationResponsable()));
        return estimation;
    }

    private UserTezea convertToEntity(UserTezea user) {
        if (user == null) {
            return null;
        }
        String username = user.getUsername();
        if (userDao.checkForExistanceUsername(username)) {
            return userDao.getUserByName(username);
        }
        throw new RuntimeException("Could not find user " + username);
    }

    private SmallClient convertToEntity(SmallClient client) {
        if (client == null) {
            return new SmallClient();
        }

        if (client.getId() != null) {
            Optional<SmallClient> c = clientDao.findById(client.getId());

            if (c.isPresent()) {
                c.get().updateFrom(client);
                client = c.get();
            }else {
                SmallClient sc = new SmallClient();
                sc.updateFrom(client);
                return sc;
            }

        }
        return client;
    }

}
