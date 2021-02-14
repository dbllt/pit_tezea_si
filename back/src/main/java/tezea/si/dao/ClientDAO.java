package tezea.si.dao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tezea.si.model.business.Client;

@Repository
public interface ClientDAO extends CrudRepository<Client, Long> {
	
}
