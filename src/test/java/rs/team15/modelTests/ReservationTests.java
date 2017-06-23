package rs.team15.modelTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.team15.model.Friendship;
import rs.team15.model.MenuItem;
import rs.team15.model.Region;
import rs.team15.model.Reservation;
import rs.team15.model.Restaurant;
import rs.team15.model.TableR;
import rs.team15.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationTests {
	@Test
	public void createReservation(){
		Set<MenuItem> menuItems = new HashSet<MenuItem>();
		Set<Region> regions = new HashSet<Region>();
		Set<TableR> tables = new HashSet<TableR>();
		Restaurant u = new Restaurant(Long.parseLong("22"),"kkk",Integer.parseInt("8"),Integer.parseInt("21"), menuItems, regions);
		
		Region r = new Region("region255","FC1501",u,Integer.parseInt("2"));
		Set<TableR> s = new HashSet<TableR>(0);

		TableR t = new TableR(Double.parseDouble("100"),Double.parseDouble("100"),Double.parseDouble("200"),Double.parseDouble("50"),5);
		s.add(t);
		Reservation rest = new Reservation(u,"05.05.2017","10:00","12:00",t);
		assertNotNull(rest);
	}
	
	@Test
	public void getSetRestaurant(){
		Set<MenuItem> menuItems = new HashSet<MenuItem>();
		Set<Region> regions = new HashSet<Region>();
		Set<TableR> tables = new HashSet<TableR>();
		Restaurant u = new Restaurant(Long.parseLong("22"),"Caribic",Integer.parseInt("8"),Integer.parseInt("21"), menuItems, regions);
		
		Reservation rest = new Reservation();
		rest.setRestaurant(u);
		assertEquals(rest.getRestaurant().getName(), "Caribic");
	}
	
	@Test
	public void getSetNameRest(){
		Reservation rest = new Reservation();
		rest.setNameRest("Caribic");
		assertEquals(rest.getNameRest(), "Caribic");
	}
	
	@Test
	public void getSetDate(){
		Reservation rest = new Reservation();
		rest.setReservationDateTime("02.02.2017.");
		assertEquals(rest.getReservationDateTime(), "02.02.2017.");
	}
	@Test
	public void getSetTime(){
		Reservation rest = new Reservation();
		rest.setTime("15:00");
		assertEquals(rest.getTime(), "15:00");
	}
	@Test
	public void getSetLength(){
		Reservation rest = new Reservation();
		rest.setLength("18:00");
		assertEquals(rest.getLength(), "18:00");
	}
	
	@Test
	public void getSetStatus(){
		Reservation rest = new Reservation();
		rest.setStatus("pending");
		assertEquals(rest.getStatus(), "pending");
	}
	
	@Test
	public void getSetUser(){
		User u = new User(Long.parseLong("1"),"milana.becejac@gmail.com","Milana","Becejac","pass");
		Reservation f = new Reservation();
		f.setUid(u);
		assertEquals(f.getUid().getFirstName(), "Milana");
	}
	
	@Test
	public void getSetTable(){
		Reservation f = new Reservation();
		Set<TableR> s = new HashSet<TableR>(0);

		TableR t = new TableR(Double.parseDouble("100"),Double.parseDouble("100"),Double.parseDouble("200"),Double.parseDouble("50"),5);
		s.add(t);
		f.setTid(t);
		assertNotNull(f.getTid());
	}
}
