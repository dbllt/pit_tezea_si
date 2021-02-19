package tezea.si.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tezea.si.model.business.request.Estimation;

@Repository
public interface EstimationDAO extends JpaRepository<Estimation, Long> {

}
