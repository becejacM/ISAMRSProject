package rs.team15.controller;

import java.text.ParseException;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.team15.model.Reservation;
import rs.team15.model.Restaurant;
import rs.team15.model.TableR;
import rs.team15.service.ReservationService;
import rs.team15.service.RestaurantService;
import rs.team15.service.TableService;

@RestController
public class ReservationController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
 	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private TableService tableService;
	@RequestMapping(
            value    = "/api/reservations/reserve/{datum:.+}/{vreme:.+}/{trajanje:.+}/{id:.+}/{idStola:.+}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
		)
	public ResponseEntity<Reservation> reserve(@PathVariable String datum,@PathVariable String vreme,@PathVariable String trajanje,@PathVariable String id,@PathVariable String idStola) throws ParseException {
		Reservation r = new Reservation();
		Restaurant rest = restaurantService.findById(id);
		logger.info(idStola);
		TableR t = tableService.findByrno(Integer.parseInt(idStola));
		
		r.setReservationDateTime(datum);
		r.setTime(vreme);
		r.setLength(trajanje);
		r.setRestaurant(rest);
		r.setTid(t);
		
		Reservation rr = reservationService.create(r);
		return new ResponseEntity<Reservation>(r, HttpStatus.OK);
	}

	@RequestMapping(
            value    = "/api/reservations",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Collection<Reservation>> getRes() {
		logger.info("> get res");
		Collection<Reservation> rest = reservationService.findAll();
		System.out.println(rest.size());
		for(Reservation r :rest){
			System.out.println(r.getReservationDateTime());
		}
		logger.info("< get res");
		return new ResponseEntity<Collection<Reservation>>(rest, HttpStatus.OK);
	}
}
