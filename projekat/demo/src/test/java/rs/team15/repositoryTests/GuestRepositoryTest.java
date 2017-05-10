package rs.team15.repositoryTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import rs.team15.model.Guest;
import rs.team15.model.User;
import rs.team15.repository.GuestRepository;
import rs.team15.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GuestRepositoryTest {

	@Autowired
	GuestRepository guestRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Test
	public void createGuestRepository(){
		Guest g = guestRepository.save(new Guest(Long.parseLong("3"),"marko.stajic@gmail.com","Marko","Stajic","pass"));
		assertNotNull(g);
	}
}
