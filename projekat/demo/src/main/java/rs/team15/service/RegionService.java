package rs.team15.service;

import rs.team15.model.Region;

public interface RegionService {
	Region create(Region region);
	
	Region findByRegno();
}
