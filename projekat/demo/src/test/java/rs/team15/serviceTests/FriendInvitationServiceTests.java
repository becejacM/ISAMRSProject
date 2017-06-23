package rs.team15.serviceTests;

import static org.junit.Assert.assertNotNull;

import java.util.Collection;

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

		User u = userService.findByEmail("guest1@gmail.com");
		System.out.println(u.getEmail());
		User u2 = userService.findByEmail("guest33@gmail.com");
		Reservation r = reservationService.findByResId(Long.parseLong("1"));
		FriendInvitation f = guestService.addFriendInvite(u.getId(), u2.getId(), r);
		assertNotNull(f);
	}
	
	
	@Test
	public void findRequestsTest(){
		Collection<FriendInvitation> users = guestService.findFI(Long.parseLong("9"));
		assertNotNull(users);
	}
	
	
	
	@Test
	public void getFriendInvitationIAccpetTest(){
		Collection<FriendInvitation> f = guestService.findFIAccept("accept",Long.parseLong("9"));
		assertNotNull(f);
	}
	
	@Test
	public void getByResIdTest(){
		Collection<FriendInvitation> f = guestService.getByReservation_rsid(Long.parseLong("1"));
		assertNotNull(f);
	}
	
	
	@Test
	public void accept(){

		User u = userService.findByEmail("guest2@gmail.com");
		System.out.println(u.getEmail());
		User u2 = userService.findByEmail("guest33@gmail.com");
		Reservation r = reservationService.findByResId(Long.parseLong("1"));
		FriendInvitation f = guestService.addFriendInvite(u.getId(), u2.getId(), r);
		FriendInvitation ff = guestService.acceptInvite(f.getFid());
		assertNotNull(ff);
	}
	
	@Test
	public void reject(){

		User u = userService.findByEmail("guest3@gmail.com");
		System.out.println(u.getEmail());
		User u2 = userService.findByEmail("guest33@gmail.com");
		Reservation r = reservationService.findByResId(Long.parseLong("1"));
		FriendInvitation f = guestService.addFriendInvite(u.getId(), u2.getId(), r);
		FriendInvitation ff = guestService.rejectInvite(f.getFid());
		assertNotNull(ff);
	}
	
}
