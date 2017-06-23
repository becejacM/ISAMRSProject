package rs.team15.serviceTests;

import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import rs.team15.model.ClientOrder;
import rs.team15.model.Employee;
import rs.team15.model.OrderItem;
import rs.team15.model.Restaurant;
import rs.team15.model.TableR;
import rs.team15.service.OrderService;
import rs.team15.service.RestaurantService;
import rs.team15.service.UserService;

public class OrderServiceTest {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	RestaurantService restaurantService;
	
	@Autowired
	UserService userService;
	
	@Test
	public void newOrder(){
		Employee e = (Employee) userService.findByEmail("waiter@gmail.com");
		Restaurant r = restaurantService.findById("venecia");
		ClientOrder co = new ClientOrder(new TableR(), e, 4000, 4, r, "waiting");
		ClientOrder ci = orderService.create(co);
		assertNotNull(ci);
	}
	
	@Test
	public void findById(){
		ClientOrder co = orderService.find(4);
		assertNotNull(co);
	}
	
	@Test
	public void findByEmployee(){
		Collection<ClientOrder> co = orderService.findByEmployee("waiter@gmail.com");
		assertNotNull(co);
	}
	
	@Test
	public void findByStatusAndRestaurant(){
		Restaurant r = restaurantService.findById("venecia");
		Collection<ClientOrder> co = orderService.findByStatusAndRestaurant("waiting", r);
		assertNotNull(co);
	}
	
	@Test
	public void updateItem(){
		OrderItem item = new OrderItem();
		OrderItem oi = orderService.updateItem(item);
		assertNotNull(oi);
	}
}
