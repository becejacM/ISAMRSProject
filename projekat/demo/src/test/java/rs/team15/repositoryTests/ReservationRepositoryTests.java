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
	public void findByRestaurantTest(){
		java.util.Collection<Reservation> res = resRepository.findByUserid_Id(Long.parseLong("2"));
		assertNotNull(res);
	}
	
	@Test
	public void findAllTest(){
		java.util.Collection<Reservation> res = resRepository.findAll();
		assertNotNull(res);
	}
	
	@Test
	public void findByIdTest(){
		Reservation res = resRepository.findByReserveID(Long.parseLong("80"));
		assertNotNull(res);
	}
	
	@Test
	public void findByResIdTest(){
		Reservation res = resRepository.findByRsid(Long.parseLong("80"));
		assertNotNull(res);
	}
	
	
	@Test
	public void findByUserTest(){
		java.util.Collection<Reservation> res = resRepository.findByUserid_Id(Long.parseLong("19"));
		assertNotNull(res);
	}
}
