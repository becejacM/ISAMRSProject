package rs.team15.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.team15.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Collection<Reservation> findByRestaurant_Rid(Long restaurantId);

}