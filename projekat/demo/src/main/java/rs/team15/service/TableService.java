package rs.team15.service;

import java.util.List;
import java.util.Set;

import rs.team15.model.Region;
import rs.team15.model.TableR;

public interface TableService {

	TableR findByrno(int i);
	
	List<TableR> findTablesByRegId(Region id);
}
