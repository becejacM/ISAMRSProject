package rs.team15.service;

import rs.team15.model.RestaurantManager;
import rs.team15.model.User;

public interface RestaurantManagerService {
	RestaurantManager getRestaurantManager(Long id);

    User create(RestaurantManager rManager);
}
