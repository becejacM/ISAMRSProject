package rs.team15.repositoryTests;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.Collection;
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
import rs.team15.model.Reservation;
import rs.team15.model.Restaurant;
import rs.team15.model.RestaurantManager;
import rs.team15.model.TableR;
import rs.team15.model.User;
import rs.team15.repository.RegionRepository;
import rs.team15.repository.ReservationRepository;
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
	
	@Autowired
	ReservationRepository resRepository;
	@Test
	public void createUserRepository(){
		Set<MenuItem> menuItems = new HashSet<MenuItem>();
		Set<Region> regions = new HashSet<Region>();
		Set<TableR> tables = new HashSet<TableR>();
		Restaurant u = new Restaurant((long) 10,"Caribic",Integer.parseInt("8"),Integer.parseInt("21"), menuItems, regions);
		rRepository.save(u);
		Region r = new Region("region1","FC1501",u,Integer.parseInt("2"),tables);
		regRepository.save(r);

		TableR t = new TableR(Long.parseLong("2"),Double.parseDouble("100"),Double.parseDouble("100"),Double.parseDouble("200"),Double.parseDouble("50"),r, Integer.parseInt("4"));

		//TableR t = new TableR(Double.parseDouble("100"),Double.parseDouble("100"),Double.parseDouble("200"),Double.parseDouble("50"),r);

		tRepository.save(t);
		Set<TableR> s = new HashSet<TableR>(0);

		s.add(t);
		Reservation rest = new Reservation(u,"03.02.2017","20:00","22:00",t);
		resRepository.save(rest);
		Collection<Restaurant> res = rRepository.findAll();
		System.out.println(res.size());
		Restaurant find = rRepository.findByName("milana");
		assertEquals(find.getName(), "Milana");
		
		
	}
}
