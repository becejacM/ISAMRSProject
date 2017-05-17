package rs.team15.serviceTests;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.team15.model.Employee;
import rs.team15.model.MenuItem;
import rs.team15.model.Region;
import rs.team15.model.Restaurant;
import rs.team15.model.User;
import rs.team15.service.EmployeeService;
import rs.team15.service.RestaurantService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {
	
	@Autowired
	EmployeeService es;
	
	@Autowired
	RestaurantService rs;
	
	@Test
	public void findRestaurantTest(){
		Restaurant r = new Restaurant(Long.parseLong("10"), "Restoran1", Integer.parseInt("10"), Integer.parseInt("20"), new HashSet <MenuItem>(),
				new HashSet<Region>());
		Region reg = new Region();
		Employee e = new Employee(Long.parseLong("10"), "emp@gmail.com", "sandra", "rajanovic", "12345", new Date(), "S", "39", "cook", r, reg);
		
		User u = es.create(e);
		assertEquals(u.getFirstName(), "sandra");
		
		Restaurant res = es.getRestaurantE("emp@gmail.com");
		assertEquals(res.getName(), "Restoran1");
		
	}

}
