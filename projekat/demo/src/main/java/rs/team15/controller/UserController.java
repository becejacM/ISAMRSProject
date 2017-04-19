package rs.team15.controller;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.team15.model.Guest;
import rs.team15.model.User;
import rs.team15.service.GuestService;
import rs.team15.service.UserService;

@RestController
public class UserController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	 	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GuestService guestService;

	
	@RequestMapping(
	            value    = "/api/users",
	            method   = RequestMethod.GET,
	            produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<Collection<User>> getUsers() {
		logger.info("> get users");
		Collection<User> users = userService.findAll();
		logger.info("< get users");
		return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
	}

	@RequestMapping(
	            value    = "/api/users/{email:.+}",
	            method   = RequestMethod.GET,
	            produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Boolean> userExists(@PathVariable String email) {
		logger.info("> get user email:{}", email);
		Boolean user = userService.alreadyExists(email);
		
		logger.info("< get user email:{}", email);
		//boolean exists = userService.alreadyExists(email);
		return new ResponseEntity<Boolean>(user, HttpStatus.OK);
	}
	

	@RequestMapping(
	            value    = "api/users/register",
	            method   = RequestMethod.POST,
	            consumes = MediaType.APPLICATION_JSON_VALUE,
	            produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<User> register(@RequestBody Guest guest) {
		guest.setLogin("no");
		guest.setRole("guest");
		guest.setVerified("no");
		User created = guestService.create(guest);
		logger.info("< create user");
		return new ResponseEntity<User>(created, HttpStatus.CREATED);
	}
	    
	    
	@RequestMapping(
            value    = "api/user/update",
            method   = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> update(@RequestBody User user) {
        User updated = userService.update(user);
        return new ResponseEntity<User>(updated, HttpStatus.OK);
    }

	   
}
