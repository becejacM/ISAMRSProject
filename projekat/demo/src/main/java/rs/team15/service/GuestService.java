package rs.team15.service;

import java.util.Collection;

import rs.team15.model.Guest;
import rs.team15.model.User;


public interface GuestService {

	Guest getGuest(Long id);
	
	Collection<Guest> getGuestByFirstName(String fname);

    User create(Guest guest);
    
    Collection<Guest> findAll();
}
