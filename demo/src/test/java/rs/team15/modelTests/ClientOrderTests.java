package rs.team15.modelTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.team15.model.ClientOrder;
import rs.team15.model.Friendship;
import rs.team15.model.Reservation;
import rs.team15.model.Restaurant;
import rs.team15.model.TableR;
import rs.team15.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientOrderTests {

	@Test
	public void createClientOrder(){
		ClientOrder c = new ClientOrder();
		c.setClientId(1);
		assertNotNull(c);
	}
	
	@Test
	public void getSetDate(){
		ClientOrder c = new ClientOrder();
		c.setDate(new Date());
		assertNotNull(c.getDate());
	}
	
	@Test
	public void getSetDeadline(){
		ClientOrder c = new ClientOrder();
		c.setDeadline(new Date());
		assertNotNull(c.getDeadline());
	}
	
	@Test
	public void getSetReservation(){
		Reservation r = new Reservation();
		ClientOrder c = new ClientOrder();
		c.setReservation(r);
		assertNotNull(c.getReservation());
	}
	
	@Test
	public void getSetClientId(){
		ClientOrder c = new ClientOrder();
		c.setClientId(1);
		assertNotNull(c.getClientId());
	}
	
	@Test
	public void getSetStatus(){
		ClientOrder c = new ClientOrder();
		c.setStatus("created");
		assertEquals(c.getStatus(), "created");
	}
	
	@Test
	public void getSetRestaurant(){
		Restaurant r = new Restaurant();
		ClientOrder c = new ClientOrder();
		c.setRestaurant(r);
		assertNotNull(c.getRestaurant());
	}
	
	@Test
	public void getSetTable(){
		TableR r = new TableR();
		ClientOrder c = new ClientOrder();
		c.setTable(r);
		assertNotNull(c.getTable());
	}
}
