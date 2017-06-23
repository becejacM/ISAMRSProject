package rs.team15.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rs.team15.model.Bid;
import rs.team15.model.Restaurant;
import rs.team15.model.Wanted;

public interface WantedRepository extends JpaRepository<Wanted, Long>{

	@Query("SELECT w FROM Wanted w WHERE w.bid=:id")
	List<Wanted> findAllByBid(@Param("id") Bid id);
}
