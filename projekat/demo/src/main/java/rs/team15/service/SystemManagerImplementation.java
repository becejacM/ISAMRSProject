package rs.team15.service;

import org.springframework.beans.factory.annotation.Autowired;


import rs.team15.model.SystemManager;
import rs.team15.model.User;
import rs.team15.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.team15.repository.SystemManagerRepository;
import rs.team15.repository.UserRepository;

@Service
public class SystemManagerImplementation implements SystemManagerService{
	@Autowired
	 SystemManagerRepository systemManagerRepository;

	 @Autowired
	 UserRepository userRepository;
	 
	@Override
	public SystemManager getSystemManager(Long id) {
		// TODO Auto-generated method stub
		return systemManagerRepository.findOne(id);
	}

	@Override
	public User create(SystemManager sManager) {
		// TODO Auto-generated method stub
		return systemManagerRepository.save(sManager);
	}
	
	@Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
