package rs.team15.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.team15.model.User;
import rs.team15.repository.UserRepository;


@Service
public class UserServiceImplementation implements UserService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    UserRepository userRepository;
	
	@Override
	public Collection<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public boolean alreadyExists(String email) {
		// TODO Auto-generated method stub
		return (userRepository.findByEmail(email) == null) ? false : true;
	}

	@Override
	public User create(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public User findOne(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}

	@Override
	public User findByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return userRepository.findByEmailAndPassword(email, password);
	}

	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		User userUpdate = userRepository.findByEmail(user.getEmail());

        if (userUpdate == null)
            return null;

        userUpdate.setLastName(user.getLastName());
        userUpdate.setFirstName(user.getFirstName());

        return userRepository.save(userUpdate);
	}

}
