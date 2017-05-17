package rs.team15.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.team15.model.Cook;
import rs.team15.model.User;
import rs.team15.repository.CookRepository;

@Service
public class CookServiceImplementation implements CookService {

	@Autowired
	CookRepository cookRepository;
	
	@Override
	public Cook getCook(Long id) {
		return cookRepository.findOne(id);
	}

	@Override
	public User create(Cook cook) {
		return cookRepository.save(cook);
	}

}
