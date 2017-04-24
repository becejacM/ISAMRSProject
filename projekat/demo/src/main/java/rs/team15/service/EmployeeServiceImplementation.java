package rs.team15.service;

import org.springframework.stereotype.Service;

import rs.team15.model.Employee;
import rs.team15.model.User;
import rs.team15.repository.EmployeeRepository;

@Service
public class EmployeeServiceImplementation implements EmployeeService{
	
	EmployeeRepository employeeRepository;

	@Override
	public Employee getEmployee(Long id) {
		return employeeRepository.findOne(id);
	}

	@Override
	public User create(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	

}
