package tezea.si.dao;

import org.springframework.data.repository.CrudRepository;

import tezea.si.model.business.Client;
import tezea.si.model.business.Site;

public interface SiteDAO extends CrudRepository<Site, Long>{

}
