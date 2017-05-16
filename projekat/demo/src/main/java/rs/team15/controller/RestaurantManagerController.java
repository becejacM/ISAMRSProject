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
import rs.team15.service.RestaurantManagerService;
import rs.team15.service.RestaurantService;

@RestController
public class RestaurantManagerController {
	
private Logger logger = LoggerFactory.getLogger(this.getClass());
 	
	@Autowired
	private RestaurantManagerService restaurantManagerService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@RequestMapping(
            value    = "/api/getrestaurant/{name:.+}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
		)
	public ResponseEntity<Restaurant> getRestByName(@PathVariable String name) {
		
		Restaurant r = restaurantService.findById(name);
		logger.info("< get name:{}", name);
		return new ResponseEntity<Restaurant>(r, HttpStatus.OK);
	}

}
