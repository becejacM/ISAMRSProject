package rs.team15.repositoryTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.team15.model.OrderItem;
import rs.team15.repository.OrderItemRepository;
import rs.team15.repository.ReservationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test {
	@Autowired
	OrderItemRepository resRepository;
	@Test
	public void createUserRepository(){
		OrderItem o = new OrderItem();
		o.setAmount(22);
		resRepository.save(o);
	}
}
