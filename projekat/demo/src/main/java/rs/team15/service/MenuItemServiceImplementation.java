package rs.team15.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.team15.model.MenuItem;
import rs.team15.model.Restaurant;
import rs.team15.repository.MenuItemRepository;


@Service
public class MenuItemServiceImplementation implements MenuItemService{
	@Autowired
	MenuItemRepository menuItemRepository;
	
	@Override
	public MenuItem create(MenuItem item) {
		return menuItemRepository.save(item);
	}
	
	@Override
	public Collection<MenuItem> findAll() {
		// TODO Auto-generated method stub
		return menuItemRepository.findAll();
	}

	@Override
	public Collection<MenuItem> findByRes(Long id) {
		// TODO Auto-generated method stub
		return menuItemRepository.findByRestaurantRid(id);
	}

	@Override
	public MenuItem findOne(Long id) {
		// TODO Auto-generated method stub
		return menuItemRepository.findOne(id);
	}
	
}
