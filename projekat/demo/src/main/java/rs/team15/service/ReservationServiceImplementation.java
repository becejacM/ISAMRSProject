package rs.team15.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rs.team15.model.ClientOrder;
import rs.team15.model.OrderItem;
import rs.team15.model.Reservation;
import rs.team15.repository.ClientOrderRepository;
import rs.team15.repository.OrderItemRepository;
import rs.team15.repository.ReservationRepository;
import rs.team15.repository.RestaurantRepository;

@Service
//@Transactional(readOnly = true)
public class ReservationServiceImplementation implements ReservationService{

	@Autowired
    ReservationRepository reservationRepository;
	
	@Autowired
    ClientOrderRepository orderRepository;
	
	@Autowired
    OrderItemRepository orderItemRepository;
	@Override
	//@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
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
	//@Transactional(readOnly = false)
	public Reservation cancel(Reservation r) {
		// TODO Auto-generated method stub
		
		r.setStatus("cancel");
		
		return reservationRepository.save(r);
	}

	@Override
	public ClientOrder addOrder(ClientOrder co) {
		// TODO Auto-generated method stub
		return orderRepository.save(co);
	}

	@Override
	public OrderItem addOrderItem(OrderItem oi) {
		// TODO Auto-generated method stub
		return orderItemRepository.save(oi);
	}

	@Override
	public Collection<ClientOrder> findByReservation(Long id) {
		// TODO Auto-generated method stub
		return orderRepository.findByReservation_Rsid(id);
	}

	
}
