package rs.team15.controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

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

import rs.team15.model.Friendship;
import rs.team15.model.Guest;
import rs.team15.model.User;
import rs.team15.service.GuestService;
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

    @RequestMapping (
            value    = "/api/guest/addf/{ids:.+}/{idr:.+}",
            method   = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> addFriend(@PathVariable Long ids,@PathVariable Long idr) {
    	logger.info("ovde sam "+idr.toString()+ids.toString());
        
        if(ids==idr){
        	logger.info("tiiii");
        	Friendship ff2 = new Friendship();
        	ff2.setStatus("you");
        	return new ResponseEntity<Friendship>(ff2, HttpStatus.OK);
        }
        Friendship f = guestService.addFriend(ids, idr);
        if(f==null){
        	logger.info("postojiii");
        	Friendship ff = new Friendship();
        	ff.setStatus("exists");
        	return new ResponseEntity<Friendship>(ff, HttpStatus.OK);
        }
        return new ResponseEntity<Friendship>(f, HttpStatus.OK);
    }
    
    @RequestMapping (
            value    = "/api/guest/accept/{ids:.+}/{idr:.+}",
            method   = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> accept(@PathVariable Long ids,@PathVariable Long idr) {
    	logger.info("ovde sam prihvatam");
        Friendship f = guestService.accept(ids, idr);
        return new ResponseEntity<Friendship>(f, HttpStatus.OK);
    }
    
    @RequestMapping (
            value    = "/api/guest/reject/{ids:.+}/{idr:.+}",
            method   = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> reject(@PathVariable Long ids,@PathVariable Long idr) {
    	logger.info("ovde sam odbijam");
        Friendship f = guestService.reject(ids, idr);
        return new ResponseEntity<Friendship>(f, HttpStatus.OK);
    }
    
    @RequestMapping (
            value    = "/api/guest/deleteF/{ids:.+}/{idr:.+}",
            method   = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> deleteF(@PathVariable Long ids,@PathVariable Long idr) {
    	logger.info("ovde sam brisem");
        Friendship f = guestService.deleteF(ids, idr);
        return new ResponseEntity<Friendship>(f, HttpStatus.OK);
    }
    
    @RequestMapping(
            value    = "/api/guests/loadFriendsIAccept/{id:.+}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    		)
    public ResponseEntity<Collection<User>> loadFriendsIAccept(@PathVariable Long id) {
    	logger.info("> get friends i accept"+id);
    	Collection<User> guests = guestService.findFriendsIAccept(id);
    	logger.info("< get friends");
    	return new ResponseEntity<Collection<User>>(guests, HttpStatus.OK);
    }
    
    @RequestMapping(
            value    = "/api/guests/loadFriendsIAdd/{id:.+}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    		)
    public ResponseEntity<Collection<User>> loadFriendsIAdd(@PathVariable Long id) {
    	logger.info("> get friends i add "+id);
    	Collection<User> guests = guestService.findFriendsIAdd(id);
    	logger.info("< get friends");
    	return new ResponseEntity<Collection<User>>(guests, HttpStatus.OK);
    }
    
    @RequestMapping(
            value    = "/api/guests/loadReq/{id:.+}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    		)
    public ResponseEntity<Collection<User>> loadReq(@PathVariable Long id) {
    	logger.info("> get req "+id);
    	Collection<User> guests = guestService.findReq(id);
    	logger.info("< get req");
    	return new ResponseEntity<Collection<User>>(guests, HttpStatus.OK);
    }

}
