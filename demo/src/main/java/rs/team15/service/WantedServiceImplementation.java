package rs.team15.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.team15.model.Bid;
import rs.team15.model.Restaurant;
import rs.team15.model.Wanted;
import rs.team15.repository.WantedRepository;

@Service
public class WantedServiceImplementation implements WantedService{
	
	@Autowired
	WantedRepository wantedRepository;
	
	@Override
	public Wanted create(Wanted wanted){
		return wantedRepository.save(wanted);
	}
	
	@Override
	public List<Wanted> findAllByB(Bid id){
		return wantedRepository.findAllByBid(id);
	}

}
