package rs.team15.modelTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.team15.model.Guest;
import rs.team15.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GuestTest {
	@Test
	public void createUser(){
		Guest g = new Guest();
		assertNotNull(g);
	}
}
