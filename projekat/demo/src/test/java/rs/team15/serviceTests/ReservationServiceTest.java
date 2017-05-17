package rs.team15.serviceTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.team15.model.MenuItem;
import rs.team15.model.Region;
import rs.team15.model.Reservation;
import rs.team15.model.Restaurant;
import rs.team15.model.TableR;
import rs.team15.model.User;
import rs.team15.repository.RegionRepository;
import rs.team15.repository.RestaurantRepository;
import rs.team15.repository.TableRepository;
import rs.team15.service.ReservationService;
import rs.team15.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationServiceTest {
	
	@Autowired
	ReservationService resService;
	

	@Autowired
	RestaurantRepository rRepository;
	
	@Autowired
	RegionRepository regRepository;
	
	@Autowired
	TableRepository tRepository;
	
	@Test
	public void createResService(){
		Set<MenuItem> menuItems = new HashSet<MenuItem>();
		Set<Region> regions = new HashSet<Region>();
		Set<TableR> tables = new HashSet<TableR>();
		Restaurant u = new Restaurant("kkk",Integer.parseInt("8"),Integer.parseInt("21"), menuItems, regions);
		rRepository.save(u);
		
		Region r = new Region("region255","FC1501",u,Integer.parseInt("2"),tables);
		regRepository.save(r);
		TableR t = new TableR(Double.parseDouble("100"),Double.parseDouble("100"),Double.parseDouble("200"),Double.parseDouble("50"),5);
		tRepository.save(t);

		Reservation rest = new Reservation(u,"05.05.2017","10:00","12:00",t);
		

		resService.create(rest);
		Reservation res = resService.findByResId(Long.parseLong("2"));
		//assertEquals(res.get, "Marko");
	}
}
