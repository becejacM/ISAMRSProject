package rs.team15.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rs.team15.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Collection<Reservation> findByRestaurant_Rid(Long restaurantId);

    List<Reservation> findAll();
    
    @Query ("SELECT f FROM Reservation f WHERE f.rsid = ?1")
    Reservation findByReserveID(Long id);
    
    Reservation findByRsid(Long id);
    
    Collection<Reservation> findByUserid_Id(Long userid);
    
    Collection<Reservation> findByStatusAndUseridId(String status, Long id);
}