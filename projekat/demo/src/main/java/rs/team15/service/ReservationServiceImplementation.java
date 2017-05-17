package rs.team15.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.team15.model.Reservation;
import rs.team15.repository.ReservationRepository;
import rs.team15.repository.RestaurantRepository;

@Service
public class ReservationServiceImplementation implements ReservationService{

	@Autowired
    ReservationRepository reservationRepository;
	
	@Override
	public Reservation create(Reservation reservation) {
		// TODO Auto-generated method stub
		reservationRepository.save(reservation);
		return null;
	}

	@Override
	public Collection<Reservation> findAll() {
		// TODO Auto-generated method stub
		reservationRepository.findAll();
		return null;
	}

	@Override
	public Reservation findByResId(Long id) {
		// TODO Auto-generated method stub
		reservationRepository.findByReserveID(id);
		return null;
	}

}
