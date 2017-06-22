package rs.team15.repositoryTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.team15.model.Guest;
import rs.team15.model.User;
import rs.team15.repository.UserRepository;
import rs.team15.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;
	
	@Test
	public void createUserRepository(){
		/*User u = new User(Long.parseLong("2"),"milana.becejac@gmail.com","Milana","Becejac","pass");
		userRepository.save(u);*/
		User find = userRepository.findByEmail("milana.becejac@gmail.com");
		assertEquals(find.getFirstName(), "milana");
	}
	
	@Test
	public void findByEmail(){
		User find = userRepository.findByEmail("milana.becejac@gmail.com");
		assertEquals(find.getFirstName(), "milana");
	}
	
	@Test
	public void findByEmailAndPassword(){
		User find = userRepository.findByEmailAndPassword("milana.becejac@gmail.com","milana");
		assertEquals(find.getFirstName(), "milana");
	}
	
	@Test
	public void findByFirstName(){
		Collection<Guest> g= userRepository.findByFirstName("milana");
		assertNotNull(g);
	}
	
	@Test
	public void findByToken(){
		User find = userRepository.findByToken("cmljYXJkbWlraUBnbWFpbC5jb206bWlsYW5h");
		assertEquals(find.getFirstName(), "mill");
	}
}
