package rs.team15.service;

import rs.team15.model.User;
import rs.team15.model.Waiter;
import rs.team15.repository.WaiterRepository;

public class WaiterServiceImplementation implements WaiterService{
	
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
