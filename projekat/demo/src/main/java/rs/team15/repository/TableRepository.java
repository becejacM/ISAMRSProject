package rs.team15.repository;

import java.util.Collection;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rs.team15.model.Guest;
import rs.team15.model.TableR;


public interface TableRepository extends JpaRepository <TableR, Long> {

	
	TableR save(TableR table);
	
	TableR findByTableInRestaurantNo(Integer id);
	
	@Query ("SELECT t FROM TableR t WHERE tableInRestaurantNo=(SELECT max(tableInRestaurantNo) FROM TableR t)")
	TableR findOne();

	
	List<TableR> findAll();

	//TableR findByTableInRestaurantNo(int i);

}