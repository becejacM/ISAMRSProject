package rs.team15.service;

import rs.team15.model.Employee;
import rs.team15.model.Region;
import rs.team15.model.Restaurant;
import rs.team15.model.User;

public interface EmployeeService {
	
	Employee getEmployee(Long id);

    User create(Employee employee);
    
    Restaurant getRestaurantE(String id);

	Region getRegion(String id);

}
