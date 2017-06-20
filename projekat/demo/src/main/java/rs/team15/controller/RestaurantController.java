package rs.team15.controller;

import java.awt.Menu;
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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.team15.model.Guest;
import rs.team15.model.MenuItem;
import rs.team15.model.Region;
import rs.team15.model.Reservation;
import rs.team15.model.Restaurant;

import rs.team15.model.RestaurantManager;
import rs.team15.model.SystemManager;

import rs.team15.model.TableR;
import rs.team15.model.User;

import rs.team15.repository.TableRepository;

import rs.team15.repository.RestaurantRepository;
import rs.team15.service.MenuItemService;
import rs.team15.service.RestaurantService;
import rs.team15.service.SystemManagerService;
import rs.team15.service.UserService;

@RestController
public class RestaurantController {


	private Logger logger = LoggerFactory.getLogger(this.getClass());
 	
	@Autowired
	private RestaurantService restaurantService;
	

	@Autowired
	private UserService userService;
	
	@Autowired
	private SystemManagerService systemManagerService; 
	
	@Autowired
	private MenuItemService menuItemService;

	
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
			logger.info(r.getName());
		}
		logger.info("< get rest");
		return new ResponseEntity<Collection<Restaurant>>(rest, HttpStatus.OK);
	}
	
	@RequestMapping(
            value    = "/api/restaurants/get/{id:.+}",
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
            value    = "/api/restaurants/hours/{id:.+}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
		)
	public ResponseEntity<Collection<String>> getHours(@PathVariable String id) {
		logger.info("> get r id:{}", id);
		Restaurant r = restaurantService.findById(id);
	
		Collection<String> ret = new ArrayList<String>();
		for (int i = r.getStartTime(); i<=r.getEndTime();i++) {
			ret.add(i+":00");
			
		}
		logger.info("< get r email:{}", id);
		return new ResponseEntity<Collection<String>>(ret, HttpStatus.OK);
	}
	@RequestMapping(value = "/api/restaurants/{email}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Restaurant> CreateRestaurant(@RequestBody Restaurant restaurant,@PathVariable("email") String email) {
		logger.info("< create userrrrrrrrrrrrrrrrrrrrr "+restaurant.getName()+"   "+email);
        SystemManager manager = (SystemManager)userService.findByEmail(email);
        restaurant.setSystemManager(manager);
		restaurant.setImage("pictures/user.png");
        restaurant.setMenuItemMenu(null);
        restaurant.setRegions(null);
        Restaurant created = restaurantService.create(restaurant);
		logger.info("< create user");
		return new ResponseEntity<Restaurant>(created, HttpStatus.OK);
	}
	
	//add dishes
	@RequestMapping(value = "/api/dishes/{name}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <MenuItem> CreateDish(@RequestBody MenuItem menuItem,@PathVariable("name") String name) {
		logger.info("< create dishhhhhhhhhhhh "+name);
        Restaurant res = restaurantService.findById(name);
        menuItem.setImage("image");
        menuItem.setDeleted(false);
        menuItem.setType("dish");
        MenuItem created = menuItemService.create(menuItem);
        res.getMenuItemMenu().add(created);
		logger.info("< create user");
		return new ResponseEntity<MenuItem>(created, HttpStatus.OK);
	}
	
	//edit dishes
	@RequestMapping(
            value    = "api/dishes/edit",
            method   = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<MenuItem> editDish(@RequestBody MenuItem dish) {
		logger.info("> update dish");
        MenuItem updated = menuItemService.create(dish);
        logger.info("< update dish");
        //logger.info("hello" + updated.getEmail() + " " + updated.getPassword());
        return new ResponseEntity<MenuItem>(updated, HttpStatus.OK);
    }
	
	//listDishes
	@RequestMapping(
            value    = "/api/dishes",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Collection<MenuItem>> getDishes() {
		logger.info("> get dishes");
		Collection<MenuItem> items = menuItemService.findAll();
		Collection<MenuItem> dishes = new ArrayList<MenuItem>();
		System.out.println(items.size());
		for(MenuItem r :items){
			System.out.println(r.getName());
			logger.info(r.getType());
			if(r.getType().equals("dish")){
				dishes.add(r);
			}
		}
		System.out.println(dishes.size());
		logger.info("< get dish");
		return new ResponseEntity<Collection<MenuItem>>(dishes, HttpStatus.OK);
	}
	
	//edit drink
		@RequestMapping(
	            value    = "api/drinks/edit",
	            method   = RequestMethod.PUT,
	            consumes = MediaType.APPLICATION_JSON_VALUE,
	            produces = MediaType.APPLICATION_JSON_VALUE
	    )
	    public ResponseEntity<MenuItem> editDrink(@RequestBody MenuItem drink) {
			logger.info("> update drink");
	        MenuItem updated = menuItemService.create(drink);
	        logger.info("< update drink");
	        //logger.info("hello" + updated.getEmail() + " " + updated.getPassword());
	        return new ResponseEntity<MenuItem>(updated, HttpStatus.OK);
	    }
	
	//add drinks
		@RequestMapping(value = "/api/drinks/{name}",
	            method = RequestMethod.POST,
	            consumes = MediaType.APPLICATION_JSON_VALUE,
	            produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity <MenuItem> CreateDrink(@RequestBody MenuItem menuItem,@PathVariable("name") String name) {
			logger.info("< create dishhhhhhhhhhhh "+name);
	        Restaurant res = restaurantService.findById(name);
	        menuItem.setImage("image");
	        menuItem.setDeleted(false);
	        menuItem.setType("drink");
	        MenuItem created = menuItemService.create(menuItem);
	        res.getMenuItemMenu().add(created);
			logger.info("< create user");
			return new ResponseEntity<MenuItem>(created, HttpStatus.OK);
		}
		
		//listDishes
		@RequestMapping(
	            value    = "/api/drinks",
	            method   = RequestMethod.GET,
	            produces = MediaType.APPLICATION_JSON_VALUE
				)
		public ResponseEntity<Collection<MenuItem>> getDrinks() {
			logger.info("> get drinks");
			Collection<MenuItem> items = menuItemService.findAll();
			Collection<MenuItem> drinks = new ArrayList<MenuItem>();
			System.out.println(items.size());
			for(MenuItem r :items){
				System.out.println(r.getName());
				logger.info(r.getType());
				if(r.getType().equals("drink")){
					drinks.add(r);
				}
			}
			System.out.println(drinks.size());
			logger.info("< get drink");
			return new ResponseEntity<Collection<MenuItem>>(drinks, HttpStatus.OK);
		}


	@RequestMapping(
            value    = "/api/restaurants/getAllATables/{datum:.+}/{vreme:.+}/{trajanje:.+}/{id:.+}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
		)
	public ResponseEntity<Collection<TableR>> getAllATables(@PathVariable String datum,@PathVariable String vreme,@PathVariable String trajanje,@PathVariable String id) throws ParseException {
		logger.info("> get rrrrrrrrrrrrrrrrr id:{}", vreme);
		if(vreme.equals("undefined") || trajanje.equals("undefined") || datum.equals("undefined")){
			logger.info("nemaaa");
			Collection<TableR> ret = new ArrayList<TableR>();
			return new ResponseEntity<Collection<TableR>>(ret, HttpStatus.OK);
		}
		Restaurant r = restaurantService.findById(id);
		DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ENGLISH);
		DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		java.util.Date date = format.parse(datum+" "+vreme);
		java.util.Date date2 = format.parse(datum+" "+trajanje);
		logger.info(date.toString());
		logger.info(date2.toString());
		Collection<TableR> ret = new ArrayList<TableR>();
		Boolean available ;
		for (Iterator<Region> region = r.getRegions().iterator(); region.hasNext();) {
			System.out.println("regioooon ");
			Region sto = region.next();
			for (Iterator<TableR> item = sto.getTables().iterator(); item.hasNext();) {
				System.out.println("stoooo ");
			    TableR t = item.next();
			    available = true;
			    for (Iterator<Reservation> res = t.getReservations().iterator(); res.hasNext();) {
					System.out.println("reeez ");
				    Reservation reservation = res.next();
					java.util.Date dateOd = format.parse(reservation.getReservationDateTime()+" "+reservation.getTime());
					java.util.Date dateDo = format.parse(reservation.getReservationDateTime()+" "+reservation.getLength());
					dateOd.setMinutes(dateOd.getMinutes()-1);
					dateDo.setMinutes(dateDo.getMinutes()+1);
					logger.info(dateOd.toString()+"  mmmmmmmmmmmmmmmmmm "+reservation.getId());
					logger.info(dateDo.toString());
					if(reservation.getStatus().equals("reserved")){
						if((date.after(dateOd) && date.before(dateDo))||(date2.after(dateOd) && date2.before(dateDo)) ){
					    	available = false;
						}
					}
				    
			    }
			    if(available){
				    ret.add(t);
			    }
			}
		}
		if(ret.size()==0){
			logger.info("nemaaa");
			Collection<TableR> rt = new ArrayList<TableR>();
			return new ResponseEntity<Collection<TableR>>(rt, HttpStatus.OK);
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
			    System.out.println(t.getTableId());
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
	
	/*@RequestMapping(
            value    = "/api/restaurants/{name:.+}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
		)
	public ResponseEntity<Restaurant> getRestByName(@PathVariable String name) {
		
		Restaurant r = restaurantService.findById(name);
		logger.info("< get name:{}", name);
		return new ResponseEntity<Restaurant>(r, HttpStatus.OK);
	}*/
	
	@RequestMapping(
            value    = "/api/getregions/{id:.+}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
		)
	public ResponseEntity<Collection<Region>> getRegionsOfRestaurant(@PathVariable String id) {
		
		Restaurant r = restaurantService.findById(id);
		
		Collection<Region> regs = new ArrayList<Region>();
		for (Iterator<Region> region = r.getRegions().iterator(); region.hasNext();) {
			Region reg = region.next();
			System.out.println(reg.getRegionNo());
			regs.add(reg);
		}
		return new ResponseEntity<Collection<Region>>(regs, HttpStatus.OK);
	}
	
	@RequestMapping(
            value    = "api/users/createTable",
            method   = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
		)
	public ResponseEntity<TableR> createTable(@RequestBody TableR table) {
		table.setDeleted("no");
		
	       
		TableR created = restaurantService.create(table);
		//logger.info("< create table {}", table.getRegion().getRegId());
		return new ResponseEntity<TableR>(created, HttpStatus.CREATED);
	}
	
	@RequestMapping(
            value    = "api/users/updateTable",
            method   = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
		)
	public ResponseEntity<TableR> updateTable(@RequestBody TableR table) {
		/*table.setWidth(50.00);
		table.setHeight(50.00);
		table.setDatax(400.00);
		table.setDatay(400.00);*/
	       
		TableR updated = restaurantService.update(table);
		logger.info("< create table");
		return new ResponseEntity<TableR>(updated, HttpStatus.OK);
	}
	
	@RequestMapping(
            value    = "api/users/deleteTable",
            method   = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
		)
	public ResponseEntity<TableR> deleteTable(@RequestBody TableR table) {
	       
		TableR deleted = restaurantService.delete(table);
		logger.info("< delete table {}", table.getTableInRestaurantNo());
		
		return new ResponseEntity<TableR>(deleted, HttpStatus.OK);
	}
	
	@RequestMapping(
            value    = "/api/users/findTable/{id:.+}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
		)
	public ResponseEntity<TableR> findTableById(@PathVariable Integer id) {
		
		TableR t = restaurantService.find(id);

		return new ResponseEntity<TableR>(t, HttpStatus.OK);
	}
	
	@RequestMapping(
            value    = "api/users/updateTable2",
            method   = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
		)
	public ResponseEntity<TableR> updateTable2(@RequestBody TableR table) {
		/*table.setWidth(50.00);
		table.setHeight(50.00);
		table.setDatax(400.00);
		table.setDatay(400.00);*/
	       
		TableR updated = restaurantService.update2(table);
		logger.info("< update table");
		return new ResponseEntity<TableR>(updated, HttpStatus.OK);
	}
}
