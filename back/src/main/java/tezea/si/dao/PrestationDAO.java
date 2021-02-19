package tezea.si.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tezea.si.model.business.request.Prestation;

@Repository
public interface PrestationDAO extends JpaRepository<Prestation, Long> {

}
