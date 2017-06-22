package rs.team15.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.team15.model.Shift;
import rs.team15.model.User;
import rs.team15.repository.ShiftRepository;

@Service
public class ShiftServiceImplementation implements ShiftService{

	@Autowired
	ShiftRepository shiftRepository;
	
	@Override
	public Shift create(Shift item) {
		return shiftRepository.save(item);
	}
	
	@Override
	public List<Shift> findAll() {
		/*Metoda koja vraca sve korisnike*/
		return shiftRepository.findAll();
	}
	
	@Override
	public void delete(Shift shift){
		shiftRepository.delete(shift);
	}
}
