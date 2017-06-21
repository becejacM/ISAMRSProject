package rs.team15.service;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rs.team15.model.ClientOrder;
import rs.team15.model.OrderItem;
import rs.team15.model.Reservation;
import rs.team15.model.Restaurant;
import rs.team15.model.TableR;
import rs.team15.model.User;
import rs.team15.repository.ClientOrderRepository;
import rs.team15.repository.OrderItemRepository;
import rs.team15.repository.ReservationRepository;
import rs.team15.repository.RestaurantRepository;
import rs.team15.repository.TableRepository;
import rs.team15.repository.UserRepository;

@Service
//@Transactional(readOnly = true)
public class ReservationServiceImplementation implements ReservationService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
    ReservationRepository reservationRepository;
	
	@Autowired
    ClientOrderRepository orderRepository;
	
	@Autowired
    OrderItemRepository orderItemRepository;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	TableRepository tableRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Reservation create(String datum, String vreme, String trajanje, String id, String idStola,  String idUser) {
		// TODO Auto-generated method stub
		
		try {
			
			Reservation r = new Reservation();
			Restaurant rest = restaurantRepository.findByName(id);
			if(rest==null){
				return null;
			}
			logger.info(idStola);
			
			User u = userRepository.findByEmail(idUser);
			if(u==null){
				return null;
			}
			logger.info(u.getEmail());
			
			r.setRestaurant(rest);
			r.setUid(u);
			
			
			r.setReservationDateTime(datum);
			r.setTime(vreme);
			r.setLength(trajanje);
			
			r.setId(rest.getName());
			r.setStatus("reserved");
			
			
			TableR tt = tableRepository.findByTableInRestaurantNo(Integer.parseInt(idStola));
			TableR t= tableRepository.findOne(tt.getTableId());
			if(t==null){
				return null;
			}
			
			t.setBrRezervacija(t.getBrRezervacija()+1);
			//Thread.sleep(5000);
			tableRepository.save(t);
			
			r.setTid(t);
			Reservation rr = reservationRepository.save(r);

			System.out.println("Ovo mu je verzija posle cuvanja: " + t.getVersion());
			

			return rr;
		}
		catch (Exception e) {
			logger.info("fscfscsdcsdsd");
			e.printStackTrace();
			return null;
		}
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

	@Override
	public Collection<Reservation> findByStatusAndUserId(String status, Long id) {
		// TODO Auto-generated method stub
		return reservationRepository.findByStatusAndUseridId(status, id);
	}

	
}
