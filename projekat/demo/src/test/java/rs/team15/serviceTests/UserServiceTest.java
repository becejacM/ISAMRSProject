package rs.team15.serviceTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

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
	public void updateUserPass(){
		User u2 = userService.findByEmail("milana.becejac@gmail.com");
		u2.setFirstName("miki");
		userService.update(u2);
		User find = userService.findOne("milana.becejac@gmail.com");
		assertEquals(find.getFirstName(), "miki");
	}
	@Test
	public void updateUserFail(){
		User u2 = userService.findByEmail("milana.becejac@gmail.com");
		u2.setFirstName("miki");
		userService.update(u2);
		User find = userService.findOne("milana.becejac@gmail.com");
		assertEquals(find.getFirstName(), "Milana");
	}
	@Test
	public void createUserService(){
		//User u = new User(Long.parseLong("2"),"milana.becejac@gmail.com","Milana","Becejac","pass");
		//userService.create(u);
		User find = userService.findOne("milana.becejac@gmail.com");
		assertEquals(find.getFirstName(), "milanaaa");
	}
	
	@Test
	public void findOne(){
		User find = userService.findOne("milana.becejac@gmail.com");
		assertEquals(find.getFirstName(), "miki");
	}
	
	@Test
	public void findByEmail(){
		User find = userService.findByEmail("milana.becejac@gmail.com");
		assertEquals(find.getFirstName(), "miki");
	}
	
	@Test
	public void findByEmailAndPassword(){
		User find = userService.findByEmailAndPassword("milana.becejac@gmail.com","milana");
		assertEquals(find.getFirstName(), "miki");
	}
	
	@Test
	public void findAll(){
		Collection<User> users = userService.findAll();
		assertNotNull(users);
	}
	
	@Test
	public void findByToken(){
		User find = userService.findByToken("bWlsYW5hLmJlY2VqYWNAZ21haWwuY29tOm1pbGFu");
		assertEquals(find.getFirstName(), "miki");
	}
}
