package rs.team15.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rs.team15.model.Guest;
import rs.team15.model.User;



public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);

	User findByEmailAndPassword(String email, String password);
	
	@Query ("SELECT u FROM User u WHERE u.fname LIKE ?1 AND u.role = 'guest'")
	Collection<Guest> findByFirstName(String fname);
	
	User findByToken(String token);
}