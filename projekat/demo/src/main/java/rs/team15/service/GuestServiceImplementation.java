package rs.team15.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.team15.model.Guest;
import rs.team15.model.User;
import rs.team15.repository.GuestRepository;
import rs.team15.repository.UserRepository;

@Service
public class GuestServiceImplementation implements GuestService{

	 @Autowired
	 GuestRepository guestRepository;

	 @Autowired
	 UserRepository userRepository;
	 
	@Override
	public Guest getGuest(Long id) {
		// TODO Auto-generated method stub
		return guestRepository.findOne(id);
	}

	@Override
	public User create(Guest guest) {
		// TODO Auto-generated method stub
		return guestRepository.save(guest);
	}

	
	@Override
	public Collection<Guest> findAll() {
		// TODO Auto-generated method stub
		return guestRepository.findAll();
	}

	@Override
	public Collection<Guest> getGuestByFirstName(String fname) {
		// TODO Auto-generated method stub
		return userRepository.findByFirstName(fname);
	}
}
