package rs.team15.service;

import rs.team15.model.User;
import rs.team15.model.Waiter;

public interface WaiterService {

	Waiter getWaiter(Long id);

    User create(Waiter waiter);
}
