package rs.team15.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.team15.model.TableR;


public interface TableRepository extends JpaRepository <TableR, Long> {
	TableR findByTableInRestaurantNo(int i);
}