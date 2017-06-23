package rs.team15.serviceTests;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.team15.model.Employee;
import rs.team15.model.MenuItem;
import rs.team15.model.Region;
import rs.team15.model.Restaurant;
import rs.team15.model.RestaurantManager;
import rs.team15.model.User;
import rs.team15.service.EmployeeService;
import rs.team15.service.RestaurantManagerService;
import rs.team15.service.RestaurantService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestaurantManagerServiceTest {

	@Autowired
	RestaurantManagerService es;
	
	@Autowired
	RestaurantService rs;
	
	@Test
	public void findRestaurantTest(){
		Restaurant r = new Restaurant(Long.parseLong("10"), "Restoran1", Integer.parseInt("10"), Integer.parseInt("20"), new HashSet <MenuItem>(),
				new HashSet<Region>());
		Region reg = new Region();
		RestaurantManager e = new RestaurantManager(Long.parseLong("2"), "resmanager1@gmail.com", "res1", "manager1", "12345",r);
		
		RestaurantManager u = (RestaurantManager) es.create(e);
		assertEquals(u.getFirstName(), "res1");
		
		Restaurant res = es.getRestaurant("resmanager1@gmail.com");
		assertEquals(res.getName(), "Restoran1");
		
	}

}
