package rs.team15.modelTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.team15.model.ClientOrder;
import rs.team15.model.Employee;
import rs.team15.model.Grade;
import rs.team15.model.Restaurant;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GradeTest {
	
	@Test
	public void createGrade(){
		Grade g = new Grade(Long.parseLong("1"), Integer.parseInt("5"), Integer.parseInt("4"), 
				Integer.parseInt("3"), new Employee(), new Restaurant(), new ClientOrder());
		
		assertNotNull(g);
	}

}
