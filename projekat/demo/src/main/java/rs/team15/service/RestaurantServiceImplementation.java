package rs.team15.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.team15.model.Restaurant;
import rs.team15.model.User;
import rs.team15.repository.RestaurantRepository;
import rs.team15.repository.UserRepository;

@Service
public class RestaurantServiceImplementation implements RestaurantService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    RestaurantRepository restaurantRepository;
	
	@Override
	public Collection<Restaurant> findAll() {
		// TODO Auto-generated method stub
		return restaurantRepository.findAll();
	}

	@Override
	public Restaurant findById(String id) {
		// TODO Auto-generated method stub
		return restaurantRepository.findByName(id);
	}

}
