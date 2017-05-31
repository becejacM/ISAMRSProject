package rs.team15.serviceTests;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.team15.model.FriendInvitation;
import rs.team15.model.Reservation;
import rs.team15.model.User;
import rs.team15.repository.FriendInvitationRepository;
import rs.team15.repository.UserRepository;
import rs.team15.service.GuestService;
import rs.team15.service.ReservationService;
import rs.team15.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendInvitationServiceTests {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	FriendInvitationRepository friendRepository;
	

	@Autowired
	UserService userService;
	
	@Autowired
	GuestService guestService;
	
	@Autowired
	ReservationService reservationService;
	
	
	@Test
	public void createF(){

		User u = userService.findByEmail("milana.becejac@gmail.com");
		System.out.println(u.getEmail());
				//new User(Long.parseLong("1"),"milana.becejac@gmail.com","Milana","Becejac","pass");
		//userService.create(u);
		User u2 = userService.findByEmail("pipi@gmail.com");
		Reservation r = reservationService.findByResId(Long.parseLong("58"));
		FriendInvitation f = new FriendInvitation(u, u2,r);
		guestService.addFriendInvite(u.getId(), u2.getId(), r);
		//Collection<User> users = friendshipRepository.findRequests("pending", Long.parseLong("1"));
		assertNotNull(f);
	}
}
