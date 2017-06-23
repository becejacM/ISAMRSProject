package rs.team15.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rs.team15.model.Bid;
import rs.team15.model.Restaurant;

public interface BidRepository extends JpaRepository<Bid, Long>{
	
	@Query("SELECT b FROM Bid b WHERE b.restaurant=:id AND b.started='active'")
	List<Bid> findAllByR(@Param("id") Restaurant id);
	
	@Query("SELECT b FROM Bid b WHERE b.name=:name")
	Bid findOne(@Param("name") String name);
}
