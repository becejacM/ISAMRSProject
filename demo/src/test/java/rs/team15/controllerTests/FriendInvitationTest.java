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

import rs.team15.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendInvitationTest {

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
	public void testCall() throws Exception {
		
		this.mvc.perform(get("/api/reservations/call/guest1@gmail.com/guest2@gmail.com/3").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.sender.email", is("guest2@gmail.com")))
        .andExpect(jsonPath("$.sender.firstName", is("guest2")))
        .andExpect(jsonPath("$.sender.lastName", is("guest2")))
        .andExpect(jsonPath("$.sender.password", is("12345")));
	}
	
	@Test
	public void testFindFi() throws Exception {
		
		this.mvc.perform(get("/api/reservations/findFI/guest1@gmail.com").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
	}
	
	@Test
	public void testFinFiIAccept() throws Exception {
		
		this.mvc.perform(get("/api/reservations/findFIAccept/9").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
	}
	
	@Test
	public void testGetCalled() throws Exception {
		
		this.mvc.perform(get("/api/reservations/getCalledFriends/7").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
	}
	
	@Test
	public void accept() throws Exception {
		
		this.mvc.perform(post("/api/reservations/accept/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
	}
	
	@Test
	public void reject() throws Exception {
		
		this.mvc.perform(post("/api/reservations/reject/3").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
	}
}
