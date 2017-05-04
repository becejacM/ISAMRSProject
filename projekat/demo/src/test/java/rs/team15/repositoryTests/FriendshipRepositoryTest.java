package rs.team15.repositoryTests;

import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.team15.model.Friendship;
import rs.team15.model.User;
import rs.team15.repository.FriendshipRepository;
import rs.team15.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendshipRepositoryTest {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	FriendshipRepository friendshipRepository;
	
	@Test
	public void createFriendshipRepository(){
		User u = new User(Long.parseLong("1"),"milana.becejac@gmail.com","Milana","Becejac","pass");
		userRepository.save(u);
		User u2 = new User(Long.parseLong("2"),"rik.becejac@gmail.com","Rik","Becejac","pass");
		userRepository.save(u2);
		Friendship f = new Friendship(Long.parseLong("1"),u,u2,"pending");
		friendshipRepository.save(f);
		Collection<User> users = friendshipRepository.findFriendshipRequests("pending", Long.parseLong("1"));
		assertNotNull(f);
	}
}
