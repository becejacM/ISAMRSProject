package rs.team15.service;

import rs.team15.model.Cook;
import rs.team15.model.User;
import rs.team15.repository.CookRepository;

public class CookServiceImplementation implements CookService {

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
