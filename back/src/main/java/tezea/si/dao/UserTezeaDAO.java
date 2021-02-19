package tezea.si.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tezea.si.model.business.UserTezea;

@Repository
public interface UserTezeaDAO extends JpaRepository<UserTezea, Long> {
	@Query(value="SELECT * FROM user u WHERE u.username = :username",nativeQuery = true)
	public UserTezea getUserByName(@Param("username") String username);
	
	@Query(value="SELECT COUNT(*) FROM user u WHERE u.username = :username",nativeQuery = true)
	public BigInteger countUserByName(@Param("username") String username);

	public default boolean checkForExistanceUsername(String username) {
		return countUserByName(username).compareTo(BigInteger.ZERO)>0;
	}
}
