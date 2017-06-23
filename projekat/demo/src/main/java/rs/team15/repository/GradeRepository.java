package rs.team15.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.team15.model.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long> {
	
	Grade save(Grade grade);

}
