package rs.team15.modelTests;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.team15.model.Bill;
import rs.team15.model.ClientOrder;
import rs.team15.model.Waiter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BillTest {
	
	@Test
	public void createBill(){
		Bill b = new Bill(Long.parseLong("1"), new ClientOrder(), new Waiter(), new Date(), Double.parseDouble("2000.0"));
		assertEquals(b.getTotalPrice(), Double.parseDouble("2000.0"), 0.1);
	}

}
