package tezea.si.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import tezea.si.model.business.request.SmallRequest;

@Repository
public interface SmallRequestDAO extends JpaRepository<SmallRequest, Long>, JpaSpecificationExecutor<SmallRequest> {

}
