package rs.team15.controller;

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

import rs.team15.model.Region;
import rs.team15.model.Restaurant;
import rs.team15.service.EmployeeService;
import rs.team15.service.RestaurantManagerService;
import rs.team15.service.RestaurantService;

@RestController
public class EmployeeController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@RequestMapping(
            value    = "/api/getrestaurantE/{id:.+}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
		)
	public ResponseEntity<Restaurant> getRestaurantE(@PathVariable String id) {
		
		Restaurant r = employeeService.getRestaurantE(id);
		logger.info("< get name:{}", r.getName());
		Region rr = employeeService.getRegion(id);
		logger.info("< get name:{}", rr.getName());
		return new ResponseEntity<Restaurant>(r, HttpStatus.OK);
	}

}
