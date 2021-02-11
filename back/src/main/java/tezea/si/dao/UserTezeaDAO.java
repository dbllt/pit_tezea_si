package tezea.si.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tezea.si.model.business.UserTezea;

@Repository
public interface UserTezeaDAO extends JpaRepository<UserTezea, Long>{
	@Query(value="SELECT * FROM user u WHERE u.username = :username",nativeQuery = true)
	public UserTezea getUserByName(@Param("username") String username);
	
	@Query(value="SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM user u WHERE u.username = :username",nativeQuery = true)
	public BigInteger checkUserByName(@Param("username") String username);
}
