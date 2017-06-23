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
import rs.team15.model.MenuItem;
import rs.team15.model.OrderItem;
import rs.team15.model.Reservation;
import rs.team15.model.User;
import rs.team15.service.MenuItemService;
import rs.team15.service.ReservationService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationControllerTest {

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
	MenuItemService menuItemsService;
	
	@Autowired
	ReservationService reservationService;
	
	@Test
	public void testGetR() throws Exception {
		
		this.mvc.perform(get("/api/reservations").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
	}
	
	@Test
	public void createR() throws Exception {
		
		this.mvc.perform(get("/api/reservations/reserve/12.08.2017/15:00/18:00/venecia/1/guest1@gmail.com").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status", is("reserved")));
	}
	
	@Test
	public void testGetRByEmail() throws Exception {
		
		this.mvc.perform(get("/api/reservations/getByUserEmail/guest1@gmail.com").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
	}
	
	@Test
	public void cancel() throws Exception {
		
		this.mvc.perform(get("/api/reservations/cancel/4/7").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
	}
	
	@Test
	public void getMakedMeals() throws Exception {
		
		this.mvc.perform(get("/api/reservations/getMakedMeals/2").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
	}
	
	@Test
	public void createOrder() throws Exception {
		
		OrderItem oi = new OrderItem();
		ClientOrder ci = new ClientOrder();
		MenuItem mi = menuItemsService.findOne(1L);
		oi.setMenuItem(mi);
		oi.setAmount(2);
		oi.setState("created");
		ci.setClientId(7);
		Reservation r = reservationService.findByResId(2L);
		ci.setReservation(r);
		ci.setStatus("created");
		ci.getItems().add(oi);
		this.mvc.perform(post("/api/orders/create/1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(ci)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.status", is("created")));
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
