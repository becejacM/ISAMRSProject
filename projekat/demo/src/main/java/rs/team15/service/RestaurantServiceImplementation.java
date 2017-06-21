package rs.team15.service;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.team15.model.Region;
import rs.team15.model.Restaurant;
import rs.team15.model.TableR;
import rs.team15.model.User;
import rs.team15.repository.RegionRepository;
import rs.team15.repository.RestaurantRepository;
import rs.team15.repository.TableRepository;
import rs.team15.repository.UserRepository;

@Service
public class RestaurantServiceImplementation implements RestaurantService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    RestaurantRepository restaurantRepository;
	
	@Autowired
	TableRepository tableRepository;
	
	@Override
	public Collection<Restaurant> findAll() {
		// TODO Auto-generated method stub
		return restaurantRepository.findAll();
	}

	@Override
	public Restaurant findById(String id) {
		// TODO Auto-generated method stub
		return restaurantRepository.findByName(id);
	}
	
	@Override
	public Restaurant create(Restaurant restaurant) {
		// TODO Auto-generated method stub
		return restaurantRepository.save(restaurant);
	}

	@Override
	public Collection<Restaurant> findByNameOrType(String name, String type) {
		// TODO Auto-generated method stub
		return restaurantRepository.findByNameOrType(name,type);
	}

	@Override
	public TableR create(TableR table) {
		TableR t = tableRepository.findOne();
		int i = 1;
		if(t == null){
			table.setTableInRestaurantNo(i);
		}
		else {
			i = t.getTableInRestaurantNo() + 1;
			logger.info("<< number: {}", i);
			table.setTableInRestaurantNo(i);
		}

		return tableRepository.save(table);
	}

	@Override
	public TableR update(TableR table) {
		TableR updated = tableRepository.findByTableInRestaurantNo(table.getTableInRestaurantNo());
		if (updated == null)
			return null;
		updated.setDatax(table.getDatax());
		updated.setDatay(table.getDatay());
		/*List<TableR> list = tableRepository.findAll();
		int max = 0;
		for(TableR t : list){
			if(t.getTableInRestaurantNo() > max){
				max = t.getTableInRestaurantNo();
				logger.info("< max " + max);
			}
		}
		updated.setTableInRestaurantNo(max+1);*/
		
		return tableRepository.save(updated);
	}

	@Override
	public TableR delete(TableR table) {
		Integer id = table.getTableInRestaurantNo();
		TableR deleted = tableRepository.findByTableInRestaurantNo(id);
		if (deleted == null)
			return null;
		deleted.setDeleted("yes");
		return tableRepository.save(deleted);
	}

	@Override
	public TableR find(Integer id) {
		return tableRepository.findByTableInRestaurantNo(id);
	}

	@Override
	public TableR update2(TableR table) {
		TableR updated = tableRepository.findByTableInRestaurantNo(table.getTableInRestaurantNo());
		if (updated == null)
			return null;
		updated.setNumOfChairs(table.getNumOfChairs());
		updated.setRegion(table.getRegion());
		return tableRepository.save(updated);
	}

	/*@Override
	public Region findRegion(String id) {
		return restaurantRepository.findRegion(id);
	}*/

}
