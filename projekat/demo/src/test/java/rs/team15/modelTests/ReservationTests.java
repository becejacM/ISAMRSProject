package rs.team15.modelTests;

import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.team15.model.Friendship;
import rs.team15.model.MenuItem;
import rs.team15.model.Region;
import rs.team15.model.Reservation;
import rs.team15.model.Restaurant;
import rs.team15.model.TableR;
import rs.team15.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationTests {
	@Test
	public void createFriendship(){
		/*Set<MenuItem> menuItems = new HashSet<MenuItem>();
		Set<Region> regions = new HashSet<Region>();
		Set<TableR> tables = new HashSet<TableR>();
		Restaurant u = new Restaurant("kkk",Integer.parseInt("8"),Integer.parseInt("21"), menuItems, regions);
		
		Region r = new Region("region255","FC1501",u,Integer.parseInt("2"),tables);
		TableR t = new TableR(Double.parseDouble("100"),Double.parseDouble("100"),Double.parseDouble("200"),Double.parseDouble("50"),5);

		Reservation rest = new Reservation(u,"05.05.2017","10:00","12:00",t);
		assertNotNull(rest);*/
	}
}
