package rs.team15.service;

import java.util.Collection;
import java.util.List;

import rs.team15.model.Reservation;
import rs.team15.model.Restaurant;

public interface ReservationService {

	Reservation create(Reservation reservation);
	
	Collection<Reservation> findAll();
	
	Reservation findByResId(Long id);
	
}
