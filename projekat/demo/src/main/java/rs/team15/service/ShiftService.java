package rs.team15.service;

import java.util.Collection;
import java.util.List;

import rs.team15.model.Shift;

public interface ShiftService {

	Shift create(Shift shift);
	
	void delete(Shift shift);
	
	List<Shift> findAll();
	
}
