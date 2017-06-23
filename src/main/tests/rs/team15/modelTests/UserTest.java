package rs.team15.modelTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.team15.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

	@Test
	public void createUser(){
		User u = new User(Long.parseLong("2"),"milana.becejac@gmail.com","Milana","Becejac","pass");
		assertEquals(u.getFirstName(), "Milana");
	}
}
