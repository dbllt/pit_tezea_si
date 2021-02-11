package tezea.si.dao;

import org.springframework.stereotype.Repository;

import tezea.si.model.business.UserTezea;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserTezeaDAO extends CrudRepository<UserTezea, Long>{
}
