package rs.team15.modelTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.team15.model.Friendship;
import rs.team15.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendshipTest {

	@Test
	public void createFriendship(){
		User u = new User(Long.parseLong("1"),"milana.becejac@gmail.com","Milana","Becejac","pass");
		User u2 = new User(Long.parseLong("2"),"rik.becejac@gmail.com","Rik","Becejac","pass");
		Friendship f = new Friendship(u,u2,"pending");
		assertNotNull(f);
	}
	
	@Test
	public void getSetSender(){
		User u = new User(Long.parseLong("1"),"milana.becejac@gmail.com","Milana","Becejac","pass");
		Friendship f = new Friendship();
		f.setSender(u);
		assertEquals(f.getSender().getFirstName(), "Milana");
	}
	
	@Test
	public void getSetReceiver(){
		User u = new User(Long.parseLong("1"),"milana.becejac@gmail.com","Milana","Becejac","pass");
		Friendship f = new Friendship();
		f.setReceiver(u);
		assertEquals(f.getReceiver().getFirstName(), "Milana");
	}
	
	@Test
	public void getSetStatus(){
		Friendship f = new Friendship();
		f.setStatus("pending");
		assertEquals(f.getStatus(), "pending");
	}
}
