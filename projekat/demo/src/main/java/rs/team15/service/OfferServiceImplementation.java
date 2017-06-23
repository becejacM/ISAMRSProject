package rs.team15.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rs.team15.model.Bid;
import rs.team15.model.Offer;
import rs.team15.model.Suplier;
import rs.team15.model.Wanted;
import rs.team15.repository.OfferRepository;

@Service
public class OfferServiceImplementation implements OfferService{

	@Autowired
	OfferRepository offerRepository;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Offer create(Offer offer){
		return offerRepository.save(offer);
	}
	
	@Override
	public List<Offer> findAllByB(Bid id){
		return offerRepository.findAllByBid(id);
	}
	
	@Override
	public List<Offer> findBySuplier(Suplier email){
		return offerRepository.findAllBySup(email);
	}
}
