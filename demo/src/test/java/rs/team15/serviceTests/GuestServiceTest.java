package rs.team15.serviceTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.team15.model.Guest;
import rs.team15.model.User;
import rs.team15.service.GuestService;
import rs.team15.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GuestServiceTest {

	@Autowired
	UserService userService;
	
	@Autowired 
	GuestService GuestService;
	
	@Test
	public void createGuestServicePass(){
		//Guest u = new Guest(Long.parseLong("2"),"milana.becejac@gmail.com","Milana","Becejac","pass");
		//GuestService.create(u);
		User find = userService.findOne("guest1@gmail.com");
		
		User f = GuestService.getGuest(find.getId());
		assertEquals(f.getFirstName(), "guest1");
	}
	
	@Test
	public void createGuestServiceFail(){
		//Guest u = new Guest(Long.parseLong("3"),"marko.stajic@gmail.com","Marko","Stajic","pass");
		//GuestService.create(u);
		User find = userService.findOne("guest1@gmail.com");
		
		User f = GuestService.getGuest(find.getId());
		assertEquals(f.getFirstName(), "Milana");
	}
	
	@Test
	public void findAllPass(){
		Collection<Guest> guests = GuestService.findAll();
		
		assertNotNull(guests);
	}
	
	@Test
	public void findByFurstNamePass(){
		Collection<Guest> guests2 = GuestService.getGuestByFirstName("guest1");
		
		assertNotNull(guests2);
	}
	
	@Test
	public void findByFurstNameFail(){
		Collection<Guest> guests3 = GuestService.getGuestByFirstName("g");
		assertNotNull(guests3);
	}
	
	@Test
	public void getGuest(){
		Guest guests2 = GuestService.getGuest(Long.parseLong("7"));
		assertNotNull(guests2);
	}
}
