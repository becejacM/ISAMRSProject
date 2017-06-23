package rs.team15.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.team15.model.Suplier;
import rs.team15.model.User;
import rs.team15.repository.SuplierRepository;

@Service
public class SuplierServiceImplementation implements SuplierService{
	
	@Autowired
	SuplierRepository suplierRepository;
	
	@Override
	public User create(Suplier suplier) {
		return suplierRepository.save(suplier);
	}

}
