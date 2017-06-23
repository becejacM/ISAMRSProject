package rs.team15.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rs.team15.model.Guest;
import rs.team15.model.Region;
import rs.team15.model.User;



public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);

	User findByEmailAndPassword(String email, String password);
	
	@Query ("SELECT u FROM User u WHERE u.fname LIKE ?1 AND u.role = 'guest'")
	Collection<Guest> findByFirstName(String fname);
	
	@Query("SELECT u FROM User u WHERE u.role='cook' or u.role='waiter' or u.role='bartender'")
	List<User> findByRole();
	User findByToken(String token);
}