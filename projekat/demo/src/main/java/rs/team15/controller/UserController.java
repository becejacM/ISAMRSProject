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

import rs.team15.model.User;
import rs.team15.service.UserService;

@RestController
public class UserController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	 	
	@Autowired
	private UserService userService;

	
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
		if (user == null) {
			logger.info("nema gaaa vracam null ");
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}
		logger.info("< get user email:{}", email);
		//boolean exists = userService.alreadyExists(email);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	

	@RequestMapping(
	            value    = "api/users/register",
	            method   = RequestMethod.POST,
	            consumes = MediaType.APPLICATION_JSON_VALUE,
	            produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<User> register(@RequestBody User guest) {
		
		guest.setRole("guest");
		guest.setVerified("no");
		User created = userService.create(guest);
		logger.info("< create user");
		return new ResponseEntity<User>(created, HttpStatus.CREATED);
	}
	    
	    
	    

	   
}
