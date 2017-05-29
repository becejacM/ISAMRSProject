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
		
		return reservationRepository.save(reservation);
	}

	@Override
	public Collection<Reservation> findAll() {
		// TODO Auto-generated method stub
		
		return reservationRepository.findAll();
	}

	@Override
	public Reservation findByResId(Long id) {
		// TODO Auto-generated method stub
		
		return reservationRepository.findByRsid(id);
	}

	@Override
	public Collection<Reservation> findByUserId(Long userid) {
		// TODO Auto-generated method stub
		return reservationRepository.findByUserid_Id(userid);
	}

	@Override
	public Reservation cancel(Reservation r) {
		// TODO Auto-generated method stub
		r.setStatus("cancel");
		reservationRepository.save(r);
		return null;
	}

}
