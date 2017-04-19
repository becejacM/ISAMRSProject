package rs.team15.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.team15.model.User;



public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);

	User findByEmailAndPassword(String email, String password);
}