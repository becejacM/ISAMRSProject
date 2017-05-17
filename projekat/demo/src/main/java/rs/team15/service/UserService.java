package rs.team15.service;

import java.util.Collection;

import rs.team15.model.User;


public interface UserService {

	Collection<User> findAll();

    boolean alreadyExists(String email);

    User create(User user);

    User findOne(String email);

    User findByEmailAndPassword(String email, String password);
    
    User findByEmail(String email);

    User update(User user);
    
    void uploadUserImage(User user, String path);
    
    void createImageFromBase64String(String base64, String path);
    
    
}
