package rs.team15.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.team15.model.Guest;
import rs.team15.model.User;
import rs.team15.service.GuestService;
import rs.team15.service.RestaurantManagerService;
import rs.team15.service.UserService;

@RestController
public class GuestController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
 	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GuestService guestService;
	

	
	@RequestMapping(
	            value    = "/api/guests",
	            method   = RequestMethod.GET,
	            produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<Collection<Guest>> getGuests() {
		logger.info("> get guests");
		Collection<Guest> guests = guestService.findAll();
		logger.info("< get guests");
		return new ResponseEntity<Collection<Guest>>(guests, HttpStatus.OK);
	}
	
	@RequestMapping(
            value    = "/api/guests/{parametar:.+}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
)
		public ResponseEntity<Collection<Guest>> find(@PathVariable String parametar) {
			logger.info("> find guests"+parametar);
			Collection<Guest> guests = guestService.getGuestByFirstName(parametar);
			
			logger.info("< find guests");
			for(Guest g : guests){
				logger.info(g.getFirstName());
			}
			return new ResponseEntity<Collection<Guest>>(guests, HttpStatus.OK);
}


}
