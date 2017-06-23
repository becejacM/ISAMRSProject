package rs.team15.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import rs.team15.model.User;
import rs.team15.model.Waiter;
import rs.team15.repository.WaiterRepository;

@Service
public class WaiterServiceImplementation implements WaiterService{
	
	@Autowired
	WaiterRepository waiterRepository;

	@Override
	public Waiter getWaiter(Long id) {
		return waiterRepository.findOne(id);
	}

	@Override
	public User create(Waiter waiter) {
		return waiterRepository.save(waiter);
	}

}
