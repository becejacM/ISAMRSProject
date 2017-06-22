package rs.team15.modelTests;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.team15.model.ClientOrder;
import rs.team15.model.MenuItem;
import rs.team15.model.OrderItem;
import rs.team15.model.Reservation;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderItemsTests {

	@Test
	public void createOrderItems(){
		OrderItem c = new OrderItem();
		c.setPrice(200);
		assertNotNull(c);
	}
	
	@Test
	public void getSetOrder(){
		OrderItem oi = new OrderItem();
		ClientOrder co = new ClientOrder();
		oi.setOrder(co);
		assertNotNull(oi.getOrder());
	}
	
	@Test
	public void getSetMI(){
		OrderItem oi = new OrderItem();
		MenuItem co = new MenuItem();
		oi.setMenuItem(co);
		assertNotNull(oi.getMenuItem());
	}
	
	@Test
	public void getSetState(){
		OrderItem oi = new OrderItem();
		oi.setState("created");
		assertNotNull(oi.getState());
		
	}
	
	@Test
	public void getSetPrice(){
		
		OrderItem c = new OrderItem();
		c.setPrice(200);
		assertNotNull(c.getPrice());
	}
	
	@Test
	public void getSetAmount(){
		
		OrderItem c = new OrderItem();
		c.setAmount(2);
		assertNotNull(c.getAmount());
	}
}
