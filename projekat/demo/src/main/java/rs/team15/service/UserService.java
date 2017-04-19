package rs.team15.service;

import java.util.Collection;

import rs.team15.model.User;


public interface UserService {

	Collection<User> findAll();

    boolean alreadyExists(String email);

    User create(User user);

    User findOne(String email);

    User findByEmailAndPassword(String email, String password);

    User update(User user);
}
