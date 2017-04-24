package rs.team15.service;

import rs.team15.model.Bartender;
import rs.team15.model.User;

public interface BartenderService {
	
	Bartender getBartender(Long id);

    User create(Bartender bartender);

}
