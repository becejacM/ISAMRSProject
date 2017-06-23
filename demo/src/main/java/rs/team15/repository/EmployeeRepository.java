package rs.team15.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.team15.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	Employee findByEmail(String id);
	
	Employee findById(Long id);

}
