package rs.team15.controllerTests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import rs.team15.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

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
	public void testGetUser() throws Exception {
		
		this.mvc.perform(get("/api/users/milana.becejac@gmail.com").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.email", is("milana.becejac@gmail.com")))
        .andExpect(jsonPath("$.firstName", is("milanaaa")))
        .andExpect(jsonPath("$.lastName", is("becejac")))
        .andExpect(jsonPath("$.password", is("milana")));
	}
	
	@Test
	public void testGetUsers() throws Exception {
		
		this.mvc.perform(get("/api/users").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
	}
	

	@Test
	public void testUserExists() throws Exception {
		
		this.mvc.perform(get("/api/users/milana.becejac@gmail.com").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.email", is("milana.becejac@gmail.com")))
        .andExpect(jsonPath("$.firstName", is("milanaaa")))
        .andExpect(jsonPath("$.lastName", is("becejac")))
        .andExpect(jsonPath("$.password", is("milana")));
	}
	
	@Test
	public void testUpdateUser() throws Exception {
		
		User u = new User();
		u.setFirstName("britney");
		u.setEmail("guest2@gmail.com");
		u.setLastName("spears");
		u.setPassword("milana");
		u.setRole("guest");
		u.setImage("https://i.scdn.co/image/7fa2bde8d2837784a847f8201686b257757a749f");
		String s = asJsonString(u);
		System.out.println(s);
		this.mvc.perform(put("/api/user/update/").contentType(MediaType.APPLICATION_JSON).content(asJsonString(u)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.email", is("guest2@gmail.com")))
        .andExpect(jsonPath("$.firstName", is("britney")))
        .andExpect(jsonPath("$.lastName", is("spears")))
        .andExpect(jsonPath("$.password", is("milana")));
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
