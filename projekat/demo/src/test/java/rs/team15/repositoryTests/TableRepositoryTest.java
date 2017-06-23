package rs.team15.repositoryTests;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.team15.model.MenuItem;
import rs.team15.model.Region;
import rs.team15.model.Restaurant;
import rs.team15.model.TableR;
import rs.team15.repository.RegionRepository;
import rs.team15.repository.RestaurantRepository;
import rs.team15.repository.TableRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TableRepositoryTest {
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	RegionRepository regionRepository;
	
	@Autowired
	TableRepository tableRepository;
	
	@Test
	public void editingTables(){
		
		Set<MenuItem> menuItems = new HashSet<MenuItem>();
		Set<Region> regions = new HashSet<Region>();
		Set<TableR> tables = new HashSet<TableR>();
		
		//Restaurant r = new Restaurant((long) 10, "Atina", (int)8, (int)20, menuItems, regions);
		//restaurantRepository.save(r);
		Region reg1 = new Region("basta", "ff00ff", new Restaurant(), (int) 5);
		Region reg2 = new Region("glavni", "ff0000", new Restaurant(), (int) 6);
		regionRepository.save(reg1);
		regionRepository.save(reg2);
		TableR table = new TableR((long) 10, (double) 100, (double) 100, (double) 50, (double) 50, reg1, (int)4);
		TableR t = tableRepository.save(table);
		assertEquals(t.getRegion().getColor(), "ff00ff");
		t.setNumOfChairs((int)7);
		t.setRegion(reg2);
		TableR t2 = tableRepository.save(t);
		assertEquals(t2.getNumOfChairs().toString(), "7");
		assertEquals(t2.getRegion().getName(), "glavni");
		
	}
	

}
