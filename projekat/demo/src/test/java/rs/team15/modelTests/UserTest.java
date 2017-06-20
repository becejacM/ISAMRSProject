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
		User u = new User(Long.parseLong("1"),"milana.becejac@gmail.com","Milana","Becejac","pass");
		
		assertEquals(u.getFirstName(), "Milana");
	}
	
	@Test
	public void getSetFirstName(){
		User u = new User();
		u.setFirstName("Milana");
		assertEquals(u.getFirstName(), "Milana");
	}
	
	@Test
	public void getSetLastName(){
		User u = new User();
		u.setLastName("Becejac");
		assertEquals(u.getLastName(), "Becejac");
	}
	
	@Test
	public void getSetEmail(){
		User u = new User();
		u.setEmail("milana.becejac@gmail.com");
		assertEquals(u.getEmail(), "milana.becejac@gmail.com");
	}
	
	@Test
	public void getSetPassword(){
		User u = new User();
		u.setPassword("pass");
		assertEquals(u.getPassword(), "pass");
	}
	
	@Test
	public void getSetImage(){
		User u = new User();
		u.setImage("image.png");
		assertEquals(u.getImage(), "image.png");
	}
	
	@Test
	public void getSetRole(){
		User u = new User();
		u.setRole("guest");
		assertEquals(u.getRole(), "guest");
	}
	
	@Test
	public void getSetVerified(){
		User u = new User();
		u.setVerified("yes");
		assertEquals(u.isVerified(), "yes");
	}
	
	@Test
	public void getSetLogin(){
		User u = new User();
		u.setLogin("yes");
		assertEquals(u.isLogin(), "yes");
	}
	
	@Test
	public void getSetToken(){
		User u = new User();
		u.setToken("token");
		assertEquals(u.getToken(), "token");
	}
}
