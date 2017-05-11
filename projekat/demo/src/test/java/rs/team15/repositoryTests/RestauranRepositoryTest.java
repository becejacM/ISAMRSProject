package rs.team15.repositoryTests;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
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
import rs.team15.model.TableR;
import rs.team15.model.User;
import rs.team15.repository.RegionRepository;
import rs.team15.repository.RestaurantRepository;
import rs.team15.repository.TableRepository;
import rs.team15.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestauranRepositoryTest {

	@Autowired
	RestaurantRepository rRepository;
	
	@Autowired
	RegionRepository regRepository;
	
	@Autowired
	TableRepository tRepository;
	
	@Test
	public void createUserRepository(){
		Set<MenuItem> menuItems = new HashSet<MenuItem>();
		Set<Region> regions = new HashSet<Region>();
		Set<TableR> tables = new HashSet<TableR>();
		Restaurant u = new Restaurant(Long.parseLong("1"),"milana",Integer.parseInt("1"),Integer.parseInt("1"), menuItems, regions);
		rRepository.save(u);
		Region r = new Region(Long.parseLong("1"),"region1","blue",u,Integer.parseInt("1"),tables);
		regRepository.save(r);
		TableR t = new TableR(Long.parseLong("1"),Double.parseDouble("1"),Double.parseDouble("1"),Double.parseDouble("1"),Double.parseDouble("1"),r);
		tRepository.save(t);
		Collection<Restaurant> res = rRepository.findAll();
		System.out.println(res.size());
		Restaurant find = rRepository.findByName("milana");
		assertEquals(find.getName(), "Milana");
		
		
		//assertEquals(find.getFirstName(), "Marko");
	}
}
