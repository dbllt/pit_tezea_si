package tezea.si.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tezea.si.model.business.request.SmallEstimation;

@Repository
public interface SmallEstimationDAO extends JpaRepository<SmallEstimation, Long> {

}
