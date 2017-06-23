package rs.team15.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.session.HttpServletSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.core.Context;
import rs.team15.model.Bartender;
import rs.team15.model.Cook;
import rs.team15.model.Employee;
import rs.team15.model.Guest;
import rs.team15.model.Region;
import rs.team15.model.Restaurant;
import rs.team15.model.RestaurantManager;
import rs.team15.model.Suplier;
import rs.team15.model.SystemManager;
import rs.team15.model.User;
import rs.team15.model.Waiter;
import rs.team15.repository.UserRepository;
import rs.team15.service.BartenderService;
import rs.team15.service.CookService;
import rs.team15.service.EmployeeService;
import rs.team15.service.GuestService;
import rs.team15.service.RestaurantManagerService;
import rs.team15.service.RestaurantService;
import rs.team15.service.SuplierService;
import rs.team15.service.SystemManagerService;
import rs.team15.service.UserService;
import rs.team15.service.WaiterService;

@RestController
@SessionAttributes({"user"})
public class UserController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	 	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GuestService guestService;
	
	@Autowired
	private RestaurantManagerService restaurantManagerService;
	
	@Autowired
	private SystemManagerService systemManagerService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private CookService cookService;
	
	@Autowired
	private WaiterService waiterService;
	
	@Autowired
	private BartenderService bartenderService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private SuplierService suplierService;

	@Autowired
    MailSender mailSender;
	
	@Autowired
	private JavaMailSender javaMailSender;

	/*
	 * Koriscenje klase za ocitavanje vrednosti iz application.properties fajla
	 */
	@Autowired
	private Environment env;
	
	@RequestMapping(value = "/api/users/createSuplier/{name:.+}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
	
    public ResponseEntity<User> registerSuplier(@RequestBody Suplier suplier,@PathVariable String name) {
		Restaurant rest = restaurantService.findById(name);
		logger.info("rrrrrrrrrrrrresssssssssssssssssrssss "+rest.getName());
		suplier.setImage("pictures/user.png");
		List<Restaurant> l = suplier.getRestaurants();
		l.add(rest);
		System.out.println("restoran "+l.get(0).getName());
		suplier.setRole("suplier");
		suplier.setRestaurants(l);
		List<Restaurant> test = suplier.getRestaurants();
		System.out.println("\nrestoran "+test.get(0).getName());
		suplier.setLogin("no");
		suplier.setVerified("no");
		suplier.setFirstTime("yes");
		User u = userService.findOne(suplier.getEmail());
		if(u!=null){
			u.setMessage("User with that email allready exists");
			return new ResponseEntity<User>(u, HttpStatus.OK);
		}
		if(suplier.getFirstName().length()<3 || suplier.getFirstName().length()>10 
				|| suplier.getLastName().length()<3 || suplier.getLastName().length()>20){
			User u1 = new User();
			u1.setMessage("Size of first name or last name is incompatible");
			return new ResponseEntity<User>(u1, HttpStatus.OK);
		}
        User created = suplierService.create(suplier);
		logger.info("< create user");
		return new ResponseEntity<User>(created, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/api/users/createCook/{name:.+}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
	
    public ResponseEntity<User> registerCook(@RequestBody Cook employee,@PathVariable String name) {
		Restaurant rest = restaurantService.findById(name);
		employee.setImage("pictures/user.png");

		employee.setRestaurant(rest);
		employee.setLogin("no");
		employee.setVerified("no");
		employee.setFirstTime("yes");

		User u = userService.findOne(employee.getEmail());
		if(u!=null){
			u.setMessage("User with that email allready exists");
			return new ResponseEntity<User>(u, HttpStatus.OK);
		}
		if(employee.getFirstName().length()<3 || employee.getFirstName().length()>10 
				|| employee.getLastName().length()<3 || employee.getLastName().length()>20){
			User u1 = new User();
			u1.setMessage("Size of first name or last name is incompatible");
			return new ResponseEntity<User>(u1, HttpStatus.OK);
		}
		String token = generateToken(employee.getEmail() +":"+ employee.getPassword());
		logger.info(token);
		employee.setToken(token);
        User created = cookService.create(employee);
		logger.info("< create user");
		return new ResponseEntity<User>(created, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/api/users/createWaiter/{name:.+}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
	
    public ResponseEntity<User> registerWaiter(@RequestBody Waiter employee,@PathVariable String name) {
        Restaurant rest = (Restaurant)restaurantService.findById(name);
		employee.setImage("pictures/user.png");

		employee.setRestaurant(rest);
		employee.setLogin("no");
		employee.setVerified("no");
		employee.setFirstTime("yes");

		User u = userService.findOne(employee.getEmail());
		if(u!=null){
			u.setMessage("User with that email allready exists");
			return new ResponseEntity<User>(u, HttpStatus.OK);
		}
		if(employee.getFirstName().length()<3 || employee.getFirstName().length()>10 
				|| employee.getLastName().length()<3 || employee.getLastName().length()>20){
			User u1 = new User();
			u1.setMessage("Size of first name or last name is incompatible");
			return new ResponseEntity<User>(u1, HttpStatus.OK);
		}
		String token = generateToken(employee.getEmail() +":"+ employee.getPassword());
		logger.info(token);
		employee.setToken(token);
        User created = waiterService.create(employee);
		logger.info("< create user");
		return new ResponseEntity<User>(created, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/api/users/createBartender/{name:.+}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
	
    public ResponseEntity<User> registerBartender(@RequestBody Bartender employee,@PathVariable String name) {
        Restaurant rest = (Restaurant)restaurantService.findById(name);
		employee.setImage("pictures/user.png");

		employee.setRestaurant(rest);
		employee.setLogin("no");
		employee.setVerified("no");
		employee.setFirstTime("yes");

		User u = userService.findOne(employee.getEmail());
		if(u!=null){
			u.setMessage("User with that email allready exists");
			return new ResponseEntity<User>(u, HttpStatus.OK);
		}
		if(employee.getFirstName().length()<3 || employee.getFirstName().length()>10 
				|| employee.getLastName().length()<3 || employee.getLastName().length()>20){
			User u1 = new User();
			u1.setMessage("Size of first name or last name is incompatible");
			return new ResponseEntity<User>(u1, HttpStatus.OK);
		}
		String token = generateToken(employee.getEmail() +":"+ employee.getPassword());
		logger.info(token);
		employee.setToken(token);
        User created = bartenderService.create(employee);
		logger.info("< create user");
		return new ResponseEntity<User>(created, HttpStatus.CREATED);
	}
	
	@RequestMapping(
	            value    = "/api/users",
	            method   = RequestMethod.GET,
	            produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<Collection<User>> getUsers() {
		/*Metoda koja vraca sve korisnike*/
		logger.info("> get users");
		Collection<User> users = userService.findAll();
		logger.info("< get users");
		return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
	}
	
	@RequestMapping(
            value    = "/api/workers/{name}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<List<User>> getWorkers(@PathVariable String name) {
		/*Metoda koja vraca sve korisnike*/
		logger.info("> workeeeeeerrrrrrrrrrrrrrrrrrrrrrrsssssssssssssss");
		List<User> users = new ArrayList<User>();
		List<User> allUsers = userService.findByUserRole();
		for(int i = 0; i < allUsers.size(); i++){
			Employee emp = employeeService.getEmployee(allUsers.get(i).getId());
			if(emp.getRestaurant().getName().equals(name)){
				users.add(allUsers.get(i));
			}
		}
		logger.info("workerrrrrrrsssssssssssss" + users.get(0));
		logger.info("< get users");
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@RequestMapping(
	            value    = "/api/users/{email:.+}",
	            method   = RequestMethod.GET,
	            produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<User> userExists(@PathVariable String email) {
		/*Metoda koja vraca korisnika sa zadatim email-om*/
		logger.info("> get user email:{}", email);
		User user = userService.findOne(email);
		
		logger.info("< get user email:{}", email);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(
            value    = "/api/user/confirm/{token:.+}/{datum:.+}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
		)
	public ModelAndView verify(@PathVariable String token,@PathVariable String datum) throws ParseException {
		/*Metoda koja se poziva kada korisnik u svom email-u klikne na link*/
		logger.info("> get user verify email:{}", token);
		User user = userService.findByToken(token);
		Date sad = new Date();
		DateFormat format = new SimpleDateFormat("dd.MM", Locale.ENGLISH);
		Date d = format.parse(datum);
		d.setMonth(d.getMonth()+1);
		d.setYear(sad.getYear());
		logger.info(sad+"      "+d);
		if(sad.after(d)){
			return new ModelAndView("confirmExpire");
		}
		user.setVerified("yes");
		userService.update(user);
		logger.info("< get user verify email:{}", token);
		return new ModelAndView("confirmOK");
	}
	
	@RequestMapping(
            value    = "/api/user/confirmInvite",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
		)
	public ModelAndView confirmInvite() {
		/*Metoda koja se poziva kada korisnik u svom email-u klikne na link*/
		return new ModelAndView("friendInvite");
	}
	@RequestMapping(
	            value    = "api/users/register",
	            method   = RequestMethod.POST,
	            consumes = MediaType.APPLICATION_JSON_VALUE,
	            produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<User> register(@RequestBody Guest guest) throws InterruptedException {
		/*Metoda koja vraca reigstruje korisnika*/
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
		String token = generateToken(guest.getEmail() +":"+ guest.getPassword());
		logger.info(token);
		guest.setToken(token);
		//guest.se
        //Thread.sleep(10000);
		/*System.out.println("Slanje emaila...");

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(guest.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Registration confirm ");
		mail.setText("Hello " + guest.getFirstName() + "\n Click and verify your email : http://localhost:8080/api/user/confirm/"+guest.getEmail());
		javaMailSender.send(mail);*/
        
           
		User created = guestService.create(guest);
		logger.info("< create user");
		return new ResponseEntity<User>(created, HttpStatus.CREATED);
	}
	
	public String generateToken(String input){
		String keyStr="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

		String output = "";
        char chr1=' ';
		char chr2=' ';
		char chr3=' ';
        int enc1, enc2, enc3, enc4 = 0;
        int i = 0;        	
        logger.info("FFSZDFCS " +input.length());

        while (i < input.length()-2){
        	logger.info("FFSZDFCS " +i);
            chr1 = input.charAt(i);
            i++;
        	logger.info("FFSZDFCS " +i);
            chr2 = input.charAt(i);
            i++;
        	logger.info("FFSZDFCS " +i);
            chr3 = input.charAt(i);
            i++;

            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;

            if (chr2==' ') {
                enc3 = enc4 = 64;
            } else if (chr3==' ') {
                enc4 = 64;
            }

            output = output +
                keyStr.charAt(enc1) +
                keyStr.charAt(enc2) +
                keyStr.charAt(enc3) +
                keyStr.charAt(enc4);
            chr1 = chr2 = chr3 = ' ';
            enc1 = enc2 = enc3 = enc4 = ' ';
        } ;

        return output;
	}
	@RequestMapping(
            value    = "api/users/registerManager",
            method   = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
		)
	
	public ResponseEntity<User> registerManager(@RequestBody RestaurantManager manager) {
		manager.setLogin("no");
		manager.setRole("manager");
		manager.setVerified("no");
		manager.setImage("pictures/user.png");
		User u = userService.findOne(manager.getEmail());
		if(u!=null){
			u.setMessage("User with that email allready exists");
			return new ResponseEntity<User>(u, HttpStatus.OK);
		}
		if(manager.getFirstName().length()<3 || manager.getFirstName().length()>10 
				|| manager.getLastName().length()<3 || manager.getLastName().length()>20){
			User u1 = new User();
			u1.setMessage("Size of first name or last name is incompatible");
			return new ResponseEntity<User>(u1, HttpStatus.OK);
		}
		String token = generateToken(manager.getEmail() +":"+ manager.getPassword());
		logger.info(token);
		manager.setToken(token);
		User created = restaurantManagerService.create(manager);
		logger.info("< create user");
		return new ResponseEntity<User>(created, HttpStatus.CREATED);
	}
	
	@RequestMapping(
            value    = "api/users/registerSysManager",
            method   = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
		)
	
	public ResponseEntity<User> registerSysManager(@RequestBody SystemManager manager) {
		manager.setLogin("no");
		manager.setRole("system_manager");
		manager.setVerified("no");
		manager.setImage("pictures/user.png");
		User u = userService.findOne(manager.getEmail());
		if(u!=null){
			u.setMessage("User with that email allready exists");
			return new ResponseEntity<User>(u, HttpStatus.OK);
		}
		if(manager.getFirstName().length()<3 || manager.getFirstName().length()>10 
				|| manager.getLastName().length()<3 || manager.getLastName().length()>20){
			User u1 = new User();
			u1.setMessage("Size of first name or last name is incompatible");
			return new ResponseEntity<User>(u1, HttpStatus.OK);
		}
		String token = generateToken(manager.getEmail() +":"+ manager.getPassword());
		logger.info(token);
		manager.setToken(token);
		User created = systemManagerService.create(manager);
		logger.info("< create user");
		return new ResponseEntity<User>(created, HttpStatus.CREATED);
	}
	    
	
	@RequestMapping(
            value    = "/api/authenticate",
            method   = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> authenticate(@RequestParam(value="email") String email, @RequestParam(value="password") String password) {
		/*Metoda koja proverava da li je korisnik registrovan*/
		logger.info("> log user "+email+"  "+password);
		User u = userService.findByEmailAndPassword(email, password);
        if(u==null){
        	return new ResponseEntity<User>(u, HttpStatus.NO_CONTENT);
        }
        if(u.getRole().equals("guest") && u.isVerified().equals("no")){
        	return new ResponseEntity<User>(u, HttpStatus.NO_CONTENT);
        }
        String token = generateToken(u.getEmail() +":"+ u.getPassword());
		logger.info(token);
		u.setToken(token);
		if(u.getRole().equals("waiter") || u.getRole().equals("cook") || u.getRole().equals("bartender")){
        	Employee e = (Employee)u;
        	if(e.getFirstTime().equals("no")){
    	    	userService.update(u);
        	}
		}
		else{
			userService.update(u);
		}
        //request.setAttribute("loggeduser", u);
        
        //User user = (User) request.getAttribute("loggeduser");
        //logger.info("req "+user.getEmail());
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
		/*Metoda koja menja podatke korisnika*/
		logger.info("> update user "+user.getEmail());
		user.setVerified("yes");
		String token = generateToken(user.getEmail() +":"+ user.getPassword());
		logger.info(token);
		user.setToken(token);
        User updated = userService.update(user);
        logger.info("< update user "+user.isVerified());
        logger.info("hello" + updated.getEmail() + " " + updated.getPassword()+"  "+updated.isVerified());
        return new ResponseEntity<User>(updated, HttpStatus.OK);
    }
	
	@RequestMapping(
            value    = "api/user/upload",
            method   = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> upload(@RequestBody User u) {
		/*Metoda koja omogucava upload slike*/
		logger.info("> upload image"+u.getImage());
		u.setVerified("yes");
		userService.uploadUserImage(u,"");
		u.setImage("pictures/"+u.getFirstName()+".png");
        logger.info("< upload image"+u.getImage());
        
        User updated = userService.update(u);
        return new ResponseEntity<User>(updated, HttpStatus.OK);
    }

	   
}
