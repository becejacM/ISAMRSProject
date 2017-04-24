package rs.team15.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.team15.model.RestaurantManager;
import rs.team15.model.User;
import rs.team15.repository.RestaurantManagerRepository;
import rs.team15.repository.UserRepository;

@Service
public class RestaurantManagerImplementation implements RestaurantManagerService{
	@Autowired
	 RestaurantManagerRepository restaurantManagerRepository;

	 @Autowired
	 UserRepository userRepository;
	 
	@Override
	public RestaurantManager getRestaurantManager(Long id) {
		// TODO Auto-generated method stub
		return restaurantManagerRepository.findOne(id);
	}

	@Override
	public User create(RestaurantManager rManager) {
		// TODO Auto-generated method stub
		return restaurantManagerRepository.save(rManager);
	}
}
