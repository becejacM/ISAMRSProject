package rs.team15.repositoryTests;

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
import rs.team15.repository.FriendInvitationRepository;
import rs.team15.repository.FriendshipRepository;
import rs.team15.repository.UserRepository;
import rs.team15.service.ReservationService;
import rs.team15.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendInvitationRepositoryTests {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	FriendInvitationRepository friendRepository;
	

	@Autowired
	UserService userService;
	
	@Autowired
	ReservationService reservationService;
	
	@Test
	public void findRequestsTest(){
		Collection<FriendInvitation> users = friendRepository.findRequests("pending", Long.parseLong("9"));
		assertNotNull(users);
	}
	
	@Test
	public void findSendersTest(){
		Collection<User> users = friendRepository.findSenders("accept", Long.parseLong("9"));
		assertNotNull(users);
	}
	
	@Test
	public void findReceiversTest(){
		Collection<User> users = friendRepository.findReceivers("pending", Long.parseLong("7"));
		assertNotNull(users);
	}
	
	@Test
	public void getFriendInvitationTest(){
		FriendInvitation f = friendRepository.getFriendship("pending",Long.parseLong("8"), Long.parseLong("7"));
		assertNotNull(f);
	}
	
	@Test
	public void getFriendInvitationIAccpetTest(){
		Collection<FriendInvitation> f = friendRepository.getFIAccept("accept",Long.parseLong("9"));
		assertNotNull(f);
	}
	
	@Test
	public void getByResIdTest(){
		Collection<FriendInvitation> f = friendRepository.getByReservation_rsid(Long.parseLong("1"));
		assertNotNull(f);
	}
	
	@Test
	public void getIdTest(){
		FriendInvitation f = friendRepository.getByFid(Long.parseLong("1"));
		assertNotNull(f);
	}
	

	@Test
	public void getByReceiverIdAndReservationRsidAndStatusTest(){
		FriendInvitation f = friendRepository.getByReceiverIdAndReservationRsidAndStatus(Long.parseLong("9"), Long.parseLong("1"), "accept");
		assertNotNull(f);
	}
	
	@Test
	public void getBySenderIdAndReservationRsidTest(){
		Collection<FriendInvitation> f = friendRepository.getBySenderIdAndReservationRsid(Long.parseLong("7"),Long.parseLong("2"));
		assertNotNull(f);
	}
	@Test
	public void createF(){

		User u = userService.findByEmail("guest1@gmail.com");
		System.out.println(u.getEmail());
		User u2 = userService.findByEmail("guest2@gmail.com");
		Reservation r = reservationService.findByResId(Long.parseLong("1"));
		FriendInvitation f = new FriendInvitation(u, u2,r);
		//friendRepository.save(f);
		assertNotNull(f);
	}
	
	
}
