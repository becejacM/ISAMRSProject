package rs.team15.repositoryTests;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.team15.model.MenuItem;
import rs.team15.model.Region;
import rs.team15.model.Restaurant;
import rs.team15.model.User;
import rs.team15.repository.RestaurantRepository;
import rs.team15.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestauranRepositoryTest {

	@Autowired
	RestaurantRepository rRepository;
	
	@Test
	public void createUserRepository(){
		Set<MenuItem> menuItems = new HashSet<MenuItem>();
		Set<Region> regions = new HashSet<Region>();
		Restaurant u = new Restaurant(Integer.parseInt("1"),"milana",Integer.parseInt("1"),Integer.parseInt("1"), menuItems, regions);
		rRepository.save(u);
		Restaurant find = rRepository.findRestaurantByName("milana");
		assertEquals(find.getName(), "Milana");
		//assertEquals(find.getFirstName(), "Marko");
	}
}
