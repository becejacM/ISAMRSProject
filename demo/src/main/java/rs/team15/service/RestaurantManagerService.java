package rs.team15.service;

import rs.team15.model.Restaurant;
import rs.team15.model.RestaurantManager;
import rs.team15.model.User;

public interface RestaurantManagerService {
	RestaurantManager getRestaurantManager(Long id);

    User create(RestaurantManager rManager);
    
    Restaurant findById(String id);
    
    Restaurant getRestaurant(String id);
}
