package rs.team15.service;

import java.util.Collection;

import rs.team15.model.Guest;
import rs.team15.model.User;


public interface GuestService {

	Guest getGuest(Long id);

    User create(Guest guest);
}
