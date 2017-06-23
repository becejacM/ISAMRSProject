package rs.team15.service;

import java.util.List;

import rs.team15.model.Bid;
import rs.team15.model.Restaurant;

public interface BidService {

	Bid create(Bid bid);
	List<Bid> findAllByRest(Restaurant id);
	Bid findByName(String name);
}
