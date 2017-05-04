package rs.team15.serviceTests;

import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.team15.model.Friendship;
import rs.team15.model.User;
import rs.team15.service.GuestService;
import rs.team15.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendshipServiceTest {
	
	@Autowired
	UserService userService;
	
	@Autowired
	GuestService guestService;
	
		
	@Test
	public void createFriendshipRepository(){
		User u = new User(Long.parseLong("1"),"milana.becejac@gmail.com","Milana","Becejac","pass");
		userService.create(u);
		User u2 = new User(Long.parseLong("2"),"rik.becejac@gmail.com","Rik","Becejac","pass");
		userService.create(u2);
		Friendship f = new Friendship(Long.parseLong("1"),u,u2,"pending");
		guestService.addFriend(Long.parseLong("1"), Long.parseLong("2"));
		Collection<User> users = guestService.findReq(Long.parseLong("2"));
		assertNotNull(f);
	}

}
