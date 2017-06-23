package rs.team15.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rs.team15.model.ClientOrder;
import rs.team15.model.Grade;
import rs.team15.model.OrderItem;
import rs.team15.model.Region;
import rs.team15.model.Reservation;
import rs.team15.model.Restaurant;
import rs.team15.model.TableR;
import rs.team15.model.User;
import rs.team15.repository.ClientOrderRepository;
import rs.team15.repository.GradeRepository;
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
	
	@Autowired
	TableService tableService;
	
	@Autowired
	GradeRepository gradeRepository;
	
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
			
			r.setNameRest(rest.getName());
			r.setStatus("reserved");
			
			
			TableR tt = tableRepository.findByTableInRestaurantNo(Integer.parseInt(idStola));
			TableR t= tableRepository.findOne(tt.getTableId());
			if(t==null){
				return null;
			}
			
			
			DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ENGLISH);
			DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
			java.util.Date date = format.parse(datum+" "+vreme);
			java.util.Date date2 = format.parse(datum+" "+trajanje);
			logger.info(date.toString());
			logger.info(date2.toString());
			Collection<TableR> ret = new ArrayList<TableR>();
			Boolean available ;
			for (Iterator<Region> region = rest.getRegions().iterator(); region.hasNext();) {
				System.out.println("regioooon ");
				Region sto = region.next();
				//for (Iterator<TableR> item = sto.getTables().iterator(); item.hasNext();) {
					System.out.println("stoooo ");
				for (Iterator<TableR> item = tableService.findTablesByRegId(sto).iterator(); item.hasNext();) {
				    TableR tr = item.next();
				    if(tr.getTableId().equals(t.getTableId())){
				    	for (Iterator<Reservation> res = tr.getReservations().iterator(); res.hasNext();) {
							System.out.println("reeez ");
						    Reservation reservation = res.next();
							java.util.Date dateOd = format.parse(reservation.getReservationDateTime()+" "+reservation.getTime());
							java.util.Date dateDo = format.parse(reservation.getReservationDateTime()+" "+reservation.getLength());
							dateOd.setMinutes(dateOd.getMinutes()-1);
							dateDo.setMinutes(dateDo.getMinutes()+1);
							logger.info(dateOd.toString()+"  mmmmmmmmmmmmmmmmmm "+reservation.getNameRest());
							logger.info(dateDo.toString());
							if(reservation.getStatus().equals("reserved")){
								if((date.after(dateOd) && date.before(dateDo))||(date2.after(dateOd) && date2.before(dateDo)) ){
							    	return null;
								}
							}
						    
					    }
				    }
				    
				}
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
		
		Collection<ClientOrder> co = orderRepository.findByReservation_Rsid(r.getReservationId());
		for(ClientOrder cc : co){
			Collection<OrderItem> o = orderItemRepository.findByOrder_Oid(cc.getOrderId());
			for(OrderItem oo : o){
				orderItemRepository.delete(oo);
			}
			orderRepository.delete(cc);
		}
		
		r.setStatus("cancel");
	
		return reservationRepository.save(r);
	}

	@Override
	public ClientOrder addOrder(ClientOrder co) {
		// TODO Auto-generated method stub
		ClientOrder o = orderRepository.findOne();
		int i = 1;
		if(o == null){
			co.setOrderNumber(i);
		}
		else {
			i = o.getOrderNumber() + 1;
			logger.info("<< number: {}", i);
			co.setOrderNumber(i);
		}
		
		return orderRepository.save(co);
	}

	@Override
	public OrderItem addOrderItem(OrderItem oi) {
		
		OrderItem ii = orderItemRepository.findOne();
		int i = 1;
		if(ii == null){
			oi.setItemNumber(i);
		}
		else {
			i = ii.getItemNumber() + 1;
			logger.info("<< number: {}", i);
			oi.setItemNumber(i);
		}
		
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

	@Override
	public Grade add(Grade grade) {
		return gradeRepository.save(grade);
	}

	@Override
	public Reservation update(Reservation reservation) {
		return reservationRepository.save(reservation);
	}

	@Override
	public Collection<Reservation> findByStatus(String status) {
		return reservationRepository.findByStatus(status);
	}

	
}
