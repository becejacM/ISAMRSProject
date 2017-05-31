package rs.team15.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.team15.model.Employee;
import rs.team15.model.Region;
import rs.team15.model.Restaurant;
import rs.team15.model.User;
import rs.team15.repository.EmployeeRepository;

@Service
public class EmployeeServiceImplementation implements EmployeeService{
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@Override
	public Employee getEmployee(Long id) {
		return employeeRepository.findOne(id);
	}

	@Override
	public User create(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Restaurant getRestaurantE(String id) {
		logger.info("< ime:{}", id);
		logger.info("< rest:{}", employeeRepository.findByEmail(id).getRestaurant().getName());
		return employeeRepository.findByEmail(id).getRestaurant();
	}
	
	@Override
	public Region getRegion(String id){
		return employeeRepository.findByEmail(id).getRegion();
	}
	
	

}
