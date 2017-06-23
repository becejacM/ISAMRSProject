package rs.team15.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rs.team15.model.Guest;

public interface GuestRepository extends JpaRepository<Guest, Long>{

	

}
