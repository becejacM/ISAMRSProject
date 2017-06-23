package rs.team15.service;

import java.util.Collection;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import rs.team15.model.ClientOrder;
import rs.team15.model.Grade;
import rs.team15.model.OrderItem;
import rs.team15.model.Reservation;
import rs.team15.model.Restaurant;
import rs.team15.model.TableR;

public interface ReservationService {

	Reservation create(String datum, String vreme, String trajanje, String id, String idStola,  String idUser);
	
	Collection<Reservation> findAll();
	
	Collection<Reservation> findByUserId(Long userid);
	
	Reservation findByResId(Long id);
	
	Reservation cancel(Reservation r);
	
	ClientOrder addOrder(ClientOrder co);
	
	OrderItem addOrderItem(OrderItem oi);
	
	Collection<ClientOrder> findByReservation(Long id);
	
	Collection<Reservation> findByStatusAndUserId(String status, Long id);
	
	Grade add(Grade grade);
	
	Reservation update(Reservation reservation);
	
	Collection<Reservation> findByStatus(String status);
}
