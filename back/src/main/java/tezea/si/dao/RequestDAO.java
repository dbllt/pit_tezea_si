package tezea.si.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tezea.si.model.business.request.Request;

@Repository
public interface RequestDAO extends JpaRepository<Request, Long> {

}
