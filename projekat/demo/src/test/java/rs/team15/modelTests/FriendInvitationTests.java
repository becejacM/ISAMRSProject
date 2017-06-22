package rs.team15.modelTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.team15.model.FriendInvitation;
import rs.team15.model.Friendship;
import rs.team15.model.Reservation;
import rs.team15.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendInvitationTests {
	@Test
	public void createFriendInvitations(){
		User u = new User(Long.parseLong("1"),"milana.becejac@gmail.com","Milana","Becejac","pass");
		User u2 = new User(Long.parseLong("2"),"rik.becejac@gmail.com","Rik","Becejac","pass");
		FriendInvitation f = new FriendInvitation(u,u2,"pending");
		assertNotNull(f);
	}
	
	@Test
	public void getSetSender(){
		User u = new User(Long.parseLong("1"),"milana.becejac@gmail.com","Milana","Becejac","pass");
		FriendInvitation f = new FriendInvitation();
		f.setSender(u);
		assertEquals(f.getSender().getFirstName(), "Milana");
	}
	
	@Test
	public void getSetReceiver(){
		User u = new User(Long.parseLong("1"),"milana.becejac@gmail.com","Milana","Becejac","pass");
		FriendInvitation f = new FriendInvitation();
		f.setReceiver(u);
		assertEquals(f.getReceiver().getFirstName(), "Milana");
	}
	
	@Test
	public void getSetStatus(){
		FriendInvitation f = new FriendInvitation();
		f.setStatus("pending");
		assertEquals(f.getStatus(), "pending");
	}
	
	@Test
	public void getSetReservation(){
		Reservation r = new Reservation();
		r.setStatus("pending");
		FriendInvitation f = new FriendInvitation();
		f.setReservation(r);
		assertEquals(f.getReservation().getStatus(), "pending");
	}
}
