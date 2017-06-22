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
	UserService userService;
	

	
	@Test
	public void createResService(){
	}
	
	@Test
	public void getReservationsByUser(){
		User u = userService.findByEmail("guest1@gmail.com");
		Collection<Reservation> res = resService.findByUserId(u.getId());
		for(Reservation r : res){
			System.out.println(r.getUid().getEmail());
		}
		assertNotNull(res);
	}
	
	@Test
	public void create(){
		Reservation res = resService.create("12.08.2017", "15:00", "18:00", "venecia", "1", "guest1@gmail.com");
		assertNotNull(res);
	}
	
	
	@Test
	public void findAllTest(){
		java.util.Collection<Reservation> res = resService.findAll();
		assertNotNull(res);
	}
	
	
	@Test
	public void findByResIdTest(){
		Reservation res = resService.findByResId(1L);
		assertNotNull(res);
	}
	
	@Test
	public void findByStatusAndUserId(){
		java.util.Collection<Reservation> res = resService.findByStatusAndUserId("created", 7L);
		assertNotNull(res);
	}
	
}
