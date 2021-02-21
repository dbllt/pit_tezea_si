package tezea.si.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import tezea.si.model.business.SmallClient;

@Repository
public interface SmallClientDAO extends JpaRepository<SmallClient, Long>, JpaSpecificationExecutor<SmallClient> {

}
