package rs.team15.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import rs.team15.model.Bartender;
import rs.team15.model.User;
import rs.team15.repository.BartenderRepository;

@Service
public class BartenderServiceImplementation implements BartenderService {

	@Autowired
	BartenderRepository bartenderRepository;
	
	@Override
	public Bartender getBartender(Long id) {
		return bartenderRepository.findOne(id);
	}

	@Override
	public User create(Bartender bartender) {
		return bartenderRepository.save(bartender);
	}

}
