package rs.team15.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rs.team15.model.Bid;
import rs.team15.model.Offer;
import rs.team15.model.Suplier;
import rs.team15.model.Wanted;

public interface OfferRepository extends JpaRepository<Offer, Long>{

	@Query("SELECT w FROM Offer w WHERE w.bid=:id")
	List<Offer> findAllByBid(@Param("id") Bid id);
	
	@Query("SELECT w FROM Offer w WHERE w.suplier=:email")
	List<Offer> findAllBySup(@Param("email") Suplier email);
}
