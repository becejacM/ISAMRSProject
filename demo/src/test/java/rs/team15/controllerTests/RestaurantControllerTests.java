package rs.team15.controllerTests;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import rs.team15.model.ClientOrder;
import rs.team15.model.Employee;
import rs.team15.model.MenuItem;
import rs.team15.model.OrderItem;
import rs.team15.model.Region;
import rs.team15.model.Reservation;
import rs.team15.model.TableR;
import rs.team15.repository.EmployeeRepository;
import rs.team15.service.MenuItemService;
import rs.team15.service.OrderService;
import rs.team15.service.RestaurantService;
import rs.team15.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestaurantControllerTests {
	
	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Autowired
	RestaurantService restaurantService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	MenuItemService menuItemsService;
	
	@Autowired
	UserService userService;
	
	@Test
	public void getOrders() throws Exception {
		
		this.mvc.perform(get("/api/orders/getAll/waiter@gmail.com").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
        
	}

	@Test
	public void getTables() throws Exception {
		this.mvc.perform(get("/api/orders/getTables/waiter@gmail.com").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());  
	}
	
	@Test
	public void saveOrder() throws Exception {
		
		OrderItem oi = new OrderItem();
		MenuItem mi = menuItemsService.findOne(1L);
		oi.setMenuItem(mi);
		oi.setAmount(5);
		oi.setPrice(oi.getMenuItem().getPrice() * oi.getAmount());
		oi.setState("waiting");
		oi.setItemNumber(10);
		ClientOrder co = new ClientOrder();
		Employee e = (Employee) userService.findByEmail("waiter@gmail.com");
		co.setEmployee(e);
		co.setTotalPrice(oi.getPrice());
		co.setOrderNumber(5);
		this.mvc.perform(post("/api/orders/addOrder").contentType(MediaType.APPLICATION_JSON).content(asJsonString(co)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.status", is("waiting")));
	}
	
	@Test
	public void createTable() throws Exception {
		
		TableR t = new TableR();
		t.setDatax(100.0);
		t.setDatay(100.0);
		t.setHeight(50.0);
		t.setWidth(50.0);
		t.setNumOfChairs(4);
		t.setTableInRestaurantNo(6);
		
		this.mvc.perform(post("api/users/createTable").contentType(MediaType.APPLICATION_JSON).content(asJsonString(t)))
        .andExpect(status().isCreated());
	}
	
	private String asJsonString(final Object obj) {
        try {
        	
            String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
}
