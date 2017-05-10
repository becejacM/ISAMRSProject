package rs.team15.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.team15.model.Region;

public interface RegionRepository extends JpaRepository <Region, Integer> {
}
