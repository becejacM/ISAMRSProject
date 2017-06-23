package rs.team15.repository;

import java.util.Collection;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rs.team15.model.Guest;
import rs.team15.model.Region;
import rs.team15.model.TableR;


public interface TableRepository extends JpaRepository <TableR, Long> {

	
	TableR save(TableR table);
	
	TableR findByTableInRestaurantNo(Integer id);
	
	@Query ("SELECT t FROM TableR t WHERE tableInRestaurantNo=(SELECT max(tableInRestaurantNo) FROM TableR t)")
	TableR findOne();
	
	TableR findByTid(Long id);

	
	List<TableR> findAll();
	
	@Query("SELECT t1 FROM TableR t1 WHERE t1.region=:id")
	List<TableR> findByRegion(@Param("id") Region id);

	//TableR findByTableInRestaurantNo(int i);

}