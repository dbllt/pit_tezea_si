package tezea.si.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tezea.si.model.business.RequestEmployee;

@Repository
public interface RequestEmployeeDAO extends JpaRepository<RequestEmployee, Long> {

}
