package rs.team15.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;

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
import rs.team15.model.Reservation;
import rs.team15.model.Restaurant;
import rs.team15.model.TableR;
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
	
	@RequestMapping(
            value    = "/api/restaurants/getAllATables/{datum:.+}/{vreme:.+}/{trajanje:.+}/{id:.+}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
		)
	public ResponseEntity<Collection<TableR>> getAllATables(@PathVariable String datum,@PathVariable String vreme,@PathVariable String trajanje,@PathVariable String id) throws ParseException {
		logger.info("> get rrrrrrrrrrrrrrrrr id:{}", id);
		Restaurant r = restaurantService.findById(id);
	
		DateFormat format = new SimpleDateFormat("MM.dd.yyyy HH:mm", Locale.ENGLISH);
		DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		java.util.Date date = format.parse(datum+" "+vreme);
		java.util.Date date2 = format.parse(datum+" "+trajanje);
		Collection<TableR> ret = new ArrayList<TableR>();
		Boolean available ;
		for (Iterator<Region> region = r.getRegions().iterator(); region.hasNext();) {
			Region sto = region.next();
			for (Iterator<TableR> item = sto.getTables().iterator(); item.hasNext();) {
			    TableR t = item.next();
			    available = true;
			    for (Iterator<Reservation> res = t.getReservations().iterator(); res.hasNext();) {
				    Reservation reservation = res.next();
					java.util.Date dateOd = format.parse(reservation.getReservationDateTime()+" "+reservation.getTime());
					java.util.Date dateDo = format.parse(reservation.getReservationDateTime()+" "+reservation.getLength());
				    if((date.after(dateOd) && date.before(dateDo))||(date2.after(dateOd) && date2.before(dateDo)) ){
				    	System.out.println("milanaaaaa");
				    	available = false;
					}
			    }
			    if(available){
				    ret.add(t);
			    }
			}
		}
		logger.info("< get r email:{}", r.getName());
		return new ResponseEntity<Collection<TableR>>(ret, HttpStatus.OK);
	}
	
	@RequestMapping(
            value    = "/api/restaurants/getAllTables/{id:.+}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
		)
	public ResponseEntity<Collection<TableR>> getAllTables(@PathVariable String id) {
		logger.info("> get r id:{}", id);
		Restaurant r = restaurantService.findById(id);
	
		Collection<TableR> ret = new ArrayList<TableR>();
		for (Iterator<Region> region = r.getRegions().iterator(); region.hasNext();) {
			Region sto = region.next();
			for (Iterator<TableR> item = sto.getTables().iterator(); item.hasNext();) {
			    TableR t = item.next();
			    System.out.println(t.getDatax());
			    ret.add(t);
			}
		}
		logger.info("< get r email:{}", r.getName());
		return new ResponseEntity<Collection<TableR>>(ret, HttpStatus.OK);
	}
	@RequestMapping(
            value    = "/api/restaurants/find/{name:.+}/{type:.+}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
		)
	public ResponseEntity<Collection<Restaurant>> getByNameOrType(@PathVariable String name,@PathVariable String type) {
		logger.info("> get r name:{}", name);
		Collection<Restaurant> r = restaurantService.findByNameOrType(name, type);
	
		logger.info("< get r name:{}", name);
		return new ResponseEntity<Collection<Restaurant>>(r, HttpStatus.OK);
	}
}
