package rs.team15.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.team15.model.Region;
import rs.team15.repository.RegionRepository;

@Service
public class RegionServiceImplementation implements RegionService{

	@Autowired
	RegionRepository regionRepository;
	
	@Override
	public Region create(Region region) {
		return regionRepository.save(region);
	}
	
	@Override
	public Region findByRegno() {
		return regionRepository.findOne();
	}
	
	@Override
	public Region findById(Integer id){
		return regionRepository.findByRegNo(id);
	}
}
