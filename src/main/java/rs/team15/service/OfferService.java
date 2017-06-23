package rs.team15.service;

import java.util.List;

import rs.team15.model.Bid;
import rs.team15.model.Offer;
import rs.team15.model.Suplier;
import rs.team15.model.Wanted;

public interface OfferService {

	Offer create(Offer offer);
	List<Offer> findAllByB(Bid id);
	
	List<Offer> findBySuplier(Suplier email);
}
