package rs.team15.repositoryTests;

import static org.junit.Assert.assertNotNull;

import org.hibernate.mapping.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.team15.model.Reservation;
import rs.team15.repository.RegionRepository;
import rs.team15.repository.ReservationRepository;
import rs.team15.repository.RestaurantRepository;
import rs.team15.repository.TableRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationRepositoryTests {

	@Autowired
	RestaurantRepository rRepository;
	
	@Autowired
	RegionRepository regRepository;
	
	@Autowired
	TableRepository tRepository;
	
	@Autowired
	ReservationRepository resRepository;
	
	@Test
	public void createUserRepository(){
		java.util.Collection<Reservation> res = resRepository.findByUserid_Id(Long.parseLong("19"));
		for(Reservation r : res){
			System.out.println(r.getUid().getEmail());
		}
		assertNotNull(res);
	}
}
