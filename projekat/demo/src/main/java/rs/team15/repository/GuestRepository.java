package rs.team15.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.team15.model.Guest;

public interface GuestRepository extends JpaRepository<Guest, Long>{

}
