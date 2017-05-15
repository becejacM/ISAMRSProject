package rs.team15.service;

import java.util.Collection;

import rs.team15.model.Restaurant;
import rs.team15.model.User;

public interface RestaurantService {

	Collection<Restaurant> findAll();
	
    Restaurant findById(String id);
    
    Restaurant create(Restaurant restaurant);

    Collection<Restaurant> findByNameOrType(String name, String type);
    
}
