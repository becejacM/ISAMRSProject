package rs.team15.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.team15.model.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {

}
