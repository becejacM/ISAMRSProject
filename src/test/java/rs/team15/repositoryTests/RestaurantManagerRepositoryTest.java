package rs.team15.repositoryTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.team15.model.Guest;
import rs.team15.model.RestaurantManager;
import rs.team15.model.User;
import rs.team15.repository.RestaurantManagerRepository;
import rs.team15.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestaurantManagerRepositoryTest {
	@Autowired
	RestaurantManagerRepository restaurantManagerRepository;
	
	@Test
	public void createRestaurantManagerRepository(){
		RestaurantManager find = restaurantManagerRepository.findByEmail("resmanager1@gmail.com");
		assertEquals(find.getFirstName(), "res1");
	}
	
	@Test
	public void findByEmail(){
		RestaurantManager find = restaurantManagerRepository.findByEmail("resmanager1@gmail.com");
		assertEquals(find.getFirstName(), "res1");
	}

}
