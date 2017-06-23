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
		User find = userRepository.findByEmail("guest1@gmail.com");
		assertEquals(find.getFirstName(), "guest1");
	}
	
	@Test
	public void findByEmail(){
		User find = userRepository.findByEmail("guest1@gmail.com");
		assertEquals(find.getFirstName(), "guest1");
	}
	
	@Test
	public void findByEmailAndPassword(){
		User find = userRepository.findByEmailAndPassword("guest1@gmail.com","12345");
		assertEquals(find.getFirstName(), "guest1");
	}
	
	@Test
	public void findByFirstName(){
		Collection<Guest> g= userRepository.findByFirstName("guest1");
		assertNotNull(g);
	}
	
	@Test
	public void findByToken(){
		User find = userRepository.findByToken("Z3Vlc3QxQGdtYWlsLmNvbToxMjM0");
		assertEquals(find.getFirstName(), "guest1");
	}
}
