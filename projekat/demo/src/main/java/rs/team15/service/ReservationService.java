package rs.team15.service;

import java.util.Collection;
import java.util.List;

import rs.team15.model.ClientOrder;
import rs.team15.model.OrderItem;
import rs.team15.model.Reservation;
import rs.team15.model.Restaurant;

public interface ReservationService {

	Reservation create(Reservation reservation);
	
	Collection<Reservation> findAll();
	
	Collection<Reservation> findByUserId(Long userid);
	
	Reservation findByResId(Long id);
	
	Reservation cancel(Reservation r);
	
	ClientOrder addOrder(ClientOrder co);
	
	OrderItem addOrderItem(OrderItem oi);
	
	Collection<ClientOrder> findByReservation(Long id);
}
