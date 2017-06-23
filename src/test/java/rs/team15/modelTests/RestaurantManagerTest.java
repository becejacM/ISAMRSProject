package rs.team15.modelTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.team15.model.Restaurant;
import rs.team15.model.RestaurantManager;
import rs.team15.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestaurantManagerTest {

	@Test
	public void createResManager(){
		Restaurant r = new Restaurant();
		RestaurantManager u = new RestaurantManager(Long.parseLong("2"),"resmanager1@gmail.com","res1","manager1","12345",r);
		assertEquals(u.getEmail(), "resmanager1@gmail.com");
	}
	
	
}
