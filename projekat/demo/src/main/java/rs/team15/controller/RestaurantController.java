package rs.team15.controller;

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

import rs.team15.model.Restaurant;
import rs.team15.model.User;
import rs.team15.service.RestaurantService;
import rs.team15.service.UserService;

@RestController
public class RestaurantController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
 	
	@Autowired
	private RestaurantService restaurantService;
	
	@RequestMapping(
            value    = "/api/restaurants",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Collection<Restaurant>> getRests() {
		logger.info("> get rest");
		Collection<Restaurant> rest = restaurantService.findAll();
		System.out.println(rest.size());
		for(Restaurant r :rest){
			System.out.println(r.getName());
		}
		logger.info("< get rest");
		return new ResponseEntity<Collection<Restaurant>>(rest, HttpStatus.OK);
	}
	
	@RequestMapping(
            value    = "/api/restaurants/{id:.+}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
		)
	public ResponseEntity<Restaurant> getRestById(@PathVariable String id) {
		logger.info("> get r id:{}", id);
		Restaurant r = restaurantService.findById(id);
	
		logger.info("< get r email:{}", id);
		return new ResponseEntity<Restaurant>(r, HttpStatus.OK);
	}
}
