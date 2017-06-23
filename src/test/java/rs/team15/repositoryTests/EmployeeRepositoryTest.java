package rs.team15.repositoryTests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.team15.model.Employee;
import rs.team15.model.Region;
import rs.team15.model.Restaurant;
import rs.team15.repository.EmployeeRepository;
import rs.team15.repository.GuestRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeRepositoryTest {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Test
	public void findById(){
		Employee e = employeeRepository.findById(Long.parseLong("4"));
		assertEquals(e.getEmail(), "waiter@gmail.com");
	}
	
	@Test
	public void findByEmail(){
		Employee e = employeeRepository.findByEmail("cook@gmail.com");
		assertEquals(e.getDressSize(), "L");
	}
	
	

}
