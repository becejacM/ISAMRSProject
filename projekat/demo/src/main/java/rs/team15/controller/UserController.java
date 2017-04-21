package rs.team15.controller;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.session.HttpServletSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import ch.qos.logback.core.Context;
import rs.team15.model.Guest;
import rs.team15.model.User;
import rs.team15.service.GuestService;
import rs.team15.service.UserService;

@RestController
@SessionAttributes({"user"})
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
	public ResponseEntity<User> userExists(@PathVariable String email) {
		logger.info("> get user email:{}", email);
		User user = userService.findOne(email);
		
		logger.info("< get user email:{}", email);
		return new ResponseEntity<User>(user, HttpStatus.OK);
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
		guest.setImage("pictures/user.png");
		User u = userService.findOne(guest.getEmail());
		if(u!=null){
			u.setMessage("User with that email allready exists");
			return new ResponseEntity<User>(u, HttpStatus.OK);
		}
		if(guest.getFirstName().length()<3 || guest.getFirstName().length()>10 
				|| guest.getLastName().length()<3 || guest.getLastName().length()>20){
			User u1 = new User();
			u1.setMessage("Size of first name or last name is incompatible");
			return new ResponseEntity<User>(u1, HttpStatus.OK);
		}
		
		User created = guestService.create(guest);
		logger.info("< create user");
		return new ResponseEntity<User>(created, HttpStatus.CREATED);
	}
	    
	
	@RequestMapping(
            value    = "/api/authenticate",
            method   = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> authenticate(@RequestParam(value="email") String email, @RequestParam(value="password") String password) {
		logger.info("> log user");
		User u = userService.findByEmailAndPassword(email, password);
        if(u==null){
        	return new ResponseEntity<User>(u, HttpStatus.NO_CONTENT);
        }
        
		logger.info("< log user "+u.getFirstName());

        return new ResponseEntity<User>(u, HttpStatus.OK);
    }
	    
	@RequestMapping(
            value    = "api/user/update",
            method   = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> update(@RequestBody User user) {
		logger.info("> update user");
        User updated = userService.update(user);
        logger.info("< update user");
        return new ResponseEntity<User>(updated, HttpStatus.OK);
    }

	   
}
