package rs.team15.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rs.team15.model.Region;

public interface RegionRepository extends JpaRepository <Region, Long> {
	
	@Query ("SELECT r FROM Region r WHERE regionNo=(SELECT max(regionNo) FROM Region r)")
	Region findOne();
}
