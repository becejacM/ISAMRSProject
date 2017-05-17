package rs.team15.modelTests;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.team15.model.Employee;
import rs.team15.model.Region;
import rs.team15.model.Restaurant;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeTest {
	
	@Test
	public void createEmployee(){
		Restaurant r = new Restaurant();
		Region reg = new Region();
		Employee e = new Employee(Long.parseLong("1"), "emp@gmail.com", "sandra", "rajanovic", "12345", new Date(), "S", "39", "cook", r, reg);
		assertEquals(e.getFirstName(), "sandra");
	}

}
