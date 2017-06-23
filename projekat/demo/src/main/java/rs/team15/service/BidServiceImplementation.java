package rs.team15.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.team15.model.Bid;
import rs.team15.model.Restaurant;
import rs.team15.repository.BidRepository;

@Service
public class BidServiceImplementation implements BidService{

	@Autowired
	BidRepository bidRepository;
	
	@Override
	public Bid create(Bid bid){
		return bidRepository.save(bid);
	}
	
	@Override
	public List<Bid> findAllByRest(Restaurant id){
		return bidRepository.findAllByR(id);
	}
	
	@Override
	public Bid findByName(String name){
		return bidRepository.findOne(name);
	}
}
