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

@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendShipControllerTests {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Test
	public void add() throws Exception {
		
		this.mvc.perform(post("/api/guest/addf/8/10").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.sender.email", is("guest2@gmail.com")))
        .andExpect(jsonPath("$.sender.firstName", is("guest2")))
        .andExpect(jsonPath("$.sender.lastName", is("guest2")))
        .andExpect(jsonPath("$.sender.password", is("12345")));
	}
	
	
	
	@Test
	public void testLoadFIAdd() throws Exception {
		
		this.mvc.perform(get("/api/guests/LoadFriendsIAdd/8").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
	}
	
	@Test
	public void testLoadFIAccept() throws Exception {
		
		this.mvc.perform(get("/api/guests/loadFriendsIAccept/7").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
	}
	
	@Test
	public void testLoadReq() throws Exception {
		
		this.mvc.perform(get("/api/guests/loadReq/8").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
	}
	
	
	
	@Test
	public void accept() throws Exception {
		
		this.mvc.perform(post("/api/guest/accept/8/9").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.sender.email", is("guest2@gmail.com")))
        .andExpect(jsonPath("$.sender.firstName", is("guest2")))
        .andExpect(jsonPath("$.sender.lastName", is("guest2")))
        .andExpect(jsonPath("$.sender.password", is("12345")));
	}
	
	@Test
	public void reject() throws Exception {
		this.mvc.perform(post("/api/guest/reject/11/7").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
	}
}
