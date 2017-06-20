package rs.team15.service;

import java.util.List;
import java.util.Set;

import javax.swing.text.TabableView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.team15.model.Region;
import rs.team15.model.TableR;
import rs.team15.repository.TableRepository;
@Service
public class TableServiceImplementation implements TableService{

	@Autowired
	TableRepository tableRepository;
	@Override
	public TableR findByrno(int i) {
		// TODO Auto-generated method stub
		return tableRepository.findByTableInRestaurantNo(i);
	}
	
	@Override
	public TableR update(TableR t) {
		// TODO Auto-generated method stub
		TableR tt =  tableRepository.findByTableInRestaurantNo(t.getTableInRestaurantNo());
		return tableRepository.save(tt);
	public List<TableR> findTablesByRegId(Region id){
		return tableRepository.findByRegion(id);
	}

}
