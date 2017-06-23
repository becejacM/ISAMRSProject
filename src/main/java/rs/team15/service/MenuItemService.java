package rs.team15.service;

import java.util.Collection;

import rs.team15.model.MenuItem;

public interface MenuItemService {

	MenuItem create(MenuItem menuItem);
	
	Collection<MenuItem> findAll();
	
	Collection<MenuItem> findByRes(Long id);
	
	MenuItem findOne(Long id);
}