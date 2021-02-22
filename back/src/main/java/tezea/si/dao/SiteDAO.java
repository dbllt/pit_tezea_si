package tezea.si.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tezea.si.model.business.Site;

public interface SiteDAO extends JpaRepository<Site, Long> {
	@Query(value = "SELECT * FROM site s WHERE s.nom = :name", nativeQuery = true)
	public Site getSiteByName(@Param("name") String name);

	@Query(value = "SELECT COUNT(*) FROM site s WHERE s.nom = :name", nativeQuery = true)
	public BigInteger countSiteByName(@Param("name") String name);

	public default boolean checkForExistanceName(String name) {
		return countSiteByName(name).compareTo(BigInteger.ZERO) > 0;
	}
}
