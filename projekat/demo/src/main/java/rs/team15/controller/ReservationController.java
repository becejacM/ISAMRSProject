package rs.team15.controller;

import java.text.ParseException;
import java.util.ArrayList;
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

import rs.team15.model.FriendInvitation;
import rs.team15.model.Guest;
import rs.team15.model.Reservation;
import rs.team15.model.Restaurant;
import rs.team15.model.TableR;
import rs.team15.model.User;
import rs.team15.service.GuestService;
import rs.team15.service.ReservationService;
import rs.team15.service.RestaurantService;
import rs.team15.service.TableService;
import rs.team15.service.UserService;

@RestController
public class ReservationController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
 	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private TableService tableService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GuestService guestService;
	
	@RequestMapping(
            value    = "/api/reservations/reserve/{datum:.+}/{vreme:.+}/{trajanje:.+}/{id:.+}/{idStola:.+}/{idUser:.+}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
		)
	public ResponseEntity<Reservation> reserve(@PathVariable String datum,@PathVariable String vreme,@PathVariable String trajanje,@PathVariable String id,@PathVariable String idStola, @PathVariable String idUser) throws ParseException {
		Reservation r = new Reservation();
		Restaurant rest = restaurantService.findById(id);
		logger.info(idStola);
		TableR t = tableService.findByrno(Integer.parseInt(idStola));
		
		User u = userService.findByEmail(idUser);
		logger.info(u.getEmail());
		
		r.setReservationDateTime(datum);
		r.setTime(vreme);
		r.setLength(trajanje);
		r.setRestaurant(rest);
		r.setTid(t);
		r.setUid(u);
		r.setId(rest.getName());
		r.setStatus("reserved");
		
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
	
	@RequestMapping(
            value    = "/api/reservations/getByUserEmail/{email:.+}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Collection<Reservation>> getResByUserEmail(@PathVariable String email) {
		logger.info("> get res by email");
		User u = userService.findByEmail(email);
		Collection<Reservation> res = reservationService.findByUserId(u.getId());
		Collection<Reservation> ret = new ArrayList<Reservation>();
		for(Reservation r : res){
			System.out.println(r.getUid().getEmail());
			if(r.getStatus().equals("reserved")){
				ret.add(r);
			}
		}
		logger.info("< get res");
		return new ResponseEntity<Collection<Reservation>>(ret, HttpStatus.OK);
	}
	
	@RequestMapping(
            value    = "/api/reservations/cancel/{reservationId:.+}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Reservation> cancel(@PathVariable String reservationId) {
		logger.info("> cancel res  "+reservationId);
		Reservation res = reservationService.findByResId(Long.parseLong(reservationId));
		logger.info("> cancel res  "+res.getId());
		Reservation r = reservationService.cancel(res);
		logger.info("> cancel res  "+r.getId());
		logger.info("< cancel res "+res.getReservationDateTime());
		return new ResponseEntity<Reservation>(res, HttpStatus.OK);
	}
	
	@RequestMapping(
            value    = "/api/reservations/findFriends/{parametar:.+}/{email:.+}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
)
		public ResponseEntity<Collection<Guest>> findFriends(@PathVariable String parametar,@PathVariable String email) {
			logger.info("> find guests"+parametar);
			Collection<Guest> guests = guestService.getGuestByFirstName(parametar);
			
			User u = userService.findByEmail(email);
			
			Collection<User> friendsIAccept = guestService.findFriendsIAccept(u.getId());
			Collection<User> friendsIAdd = guestService.findFriendsIAdd(u.getId());
			
			Collection<Guest> finded = new ArrayList<Guest>();
			logger.info("< find guests");
			for(Guest g : guests){
				logger.info(g.getFirstName());
				for(User u1 : friendsIAccept){
					if(u1.getEmail().equals(g.getEmail())){
						finded.add(g);
					}
				}
				for(User u1 : friendsIAdd){
					if(u1.getEmail().equals(g.getEmail())){
						finded.add(g);
					}
				}
			}
			return new ResponseEntity<Collection<Guest>>(finded, HttpStatus.OK);
	}
	
	@RequestMapping(
            value    = "/api/reservations/call/{parametar:.+}/{email:.+}/{rsid:.+}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
)
		public ResponseEntity<FriendInvitation> callFriend(@PathVariable String parametar,@PathVariable String email, @PathVariable String rsid) {
			logger.info("> find guests"+parametar);
			User u1 = userService.findByEmail(parametar);
			User u = userService.findByEmail(email);
			Reservation r = reservationService.findByResId(Long.parseLong(rsid));
			logger.info(r.getLength().toString());
			FriendInvitation g = guestService.addFriendInvite(u.getId(), u1.getId(),r);
			
			return new ResponseEntity<FriendInvitation>(g, HttpStatus.OK);
	}
	
	@RequestMapping(
            value    = "/api/reservations/findFI/{email:.+}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
)
		public ResponseEntity<Collection<FriendInvitation>> callFriend(@PathVariable String email) {
			logger.info("> find guests"+email);
			User u = userService.findByEmail(email);
			
			Collection<FriendInvitation> invitations = guestService.findFI(u.getId());
			
			return new ResponseEntity<Collection<FriendInvitation>>(invitations, HttpStatus.OK);
	}
}
