package rs.team15.service;

import java.util.List;

import rs.team15.model.Bid;
import rs.team15.model.Restaurant;
import rs.team15.model.Wanted;

public interface WantedService {

	Wanted create(Wanted wanted);
	List<Wanted> findAllByB(Bid id);
}
