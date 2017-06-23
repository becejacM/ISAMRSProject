package rs.team15.service;

import rs.team15.model.Cook;
import rs.team15.model.User;

public interface CookService {
	
	Cook getCook(Long id);

    User create(Cook cook);

}
