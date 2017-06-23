package rs.team15.repositoryTests;

import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.team15.model.ClientOrder;
import rs.team15.model.MenuItem;
import rs.team15.repository.ClientOrderRepository;
import rs.team15.repository.GuestRepository;
import rs.team15.repository.MenuItemRepository;
import rs.team15.repository.OrderItemRepository;
import rs.team15.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTestsForCreatingOrders {

	@Autowired
	MenuItemRepository miRepository;
	
	@Autowired
	OrderItemRepository oiRepository;
	
	@Autowired
	ClientOrderRepository coRepository;
	
	@Test
	public void findMenuItemsByRestaurantRid(){
		Collection <MenuItem> g =miRepository.findByRestaurantRid(1L);
		assertNotNull(g);
	}
	
	@Test
	public void findOrdersByRid(){
		Collection<ClientOrder> g = coRepository.findByReservation_Rsid(2L);
		assertNotNull(g);
	}
}
