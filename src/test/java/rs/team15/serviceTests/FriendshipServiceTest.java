package rs.team15.serviceTests;

import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.team15.model.FriendInvitation;
import rs.team15.model.Friendship;
import rs.team15.model.Reservation;
import rs.team15.model.User;
import rs.team15.service.GuestService;
import rs.team15.service.ReservationService;
import rs.team15.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendshipServiceTest {
	
	@Autowired
	UserService userService;
	
	@Autowired
	GuestService guestService;
	
	@Autowired
	ReservationService reservationService;
		
	@Test
	public void createFriendshipRepository(){
		User u = userService.findByEmail("guest1@gmail.com");
		System.out.println(u.getEmail());
		User u2 = userService.findByEmail("guest33@gmail.com");
		System.out.println(u2.getId());
		Friendship f = new Friendship(u,u2,"pending");
		guestService.addFriend(u.getId(), u2.getId());
		Collection<User> users = guestService.findReq(u2.getId());
		assertNotNull(users);
	}
	
	@Test
	public void findRequestsTest(){
		Collection<User> users = guestService.findReq(Long.parseLong("9"));
		assertNotNull(users);
	}
	
	@Test
	public void findSendersTest(){
		Collection<User> users = guestService.findFriendsIAccept(Long.parseLong("7"));
		assertNotNull(users);
	}
	
	@Test
	public void findReceiversTest(){
		Collection<User> users = guestService.findFriendsIAdd(Long.parseLong("8"));
		assertNotNull(users);
	}

	@Test
	public void AcceptTest(){
		User u = userService.findByEmail("guest2@gmail.com");
		System.out.println(u.getEmail());
		User u2 = userService.findByEmail("guest33@gmail.com");
		System.out.println(u2.getId());
		Friendship f = new Friendship(u,u2,"pending");
		guestService.addFriend(u.getId(), u2.getId());
		
		Friendship ff = guestService.accept(u.getId(), u2.getId());
		assertNotNull(ff);
	}
	
	@Test
	public void RejectTest(){
		User u = userService.findByEmail("guest3@gmail.com");
		System.out.println(u.getEmail());
		User u2 = userService.findByEmail("guest33@gmail.com");
		System.out.println(u2.getId());
		Friendship f = new Friendship(u,u2,"pending");
		guestService.addFriend(u.getId(), u2.getId());
		
		Friendship ff = guestService.accept(u.getId(), u2.getId());
		assertNotNull(ff);
	}
	
	
	
	
}
