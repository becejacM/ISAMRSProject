package rs.team15.serviceTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.team15.model.User;
import rs.team15.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	@Autowired
	UserService userService;
	
	@Test
	public void createUserService(){
		User u = new User(Long.parseLong("2"),"milana.becejac@gmail.com","Milana","Becejac","pass");
		userService.create(u);
		User find = userService.findOne("milana.becejac@gmail.com");
		assertEquals(find.getFirstName(), "Milana");
	}
	@Test
	public void updateUserPass(){
		User u = new User(Long.parseLong("4"),"nik.becejac@gmail.com","Milana","Becejac","pass");
		userService.create(u);
		User u2 = new User(Long.parseLong("3"),"nik.becejac@gmail.com","Milanka","Becejacccc","pass");
		userService.update(u2);
		User find = userService.findOne("nik.becejac@gmail.com");
		assertEquals(find.getFirstName(), "Milanka");
	}
	@Test
	public void updateUserFail(){
		User u = new User(Long.parseLong("3"),"mik.becejac@gmail.com","Milana","Becejac","pass");
		userService.create(u);
		User u2 = new User(Long.parseLong("3"),"mik.becejac@gmail.com","Milanka","Becejacccc","pass");
		userService.update(u2);
		User find = userService.findOne("mik.becejac@gmail.com");
		assertEquals(find.getFirstName(), "Milana");
	}
}
