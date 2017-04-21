package rs.team15.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.team15.model.User;
import rs.team15.repository.UserRepository;
import sun.misc.BASE64Decoder;


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
        userUpdate.setImage(user.getImage());
        return userRepository.save(userUpdate);
	}

	@Override
	public void uploadUserImage(User user, String path){
		/*funkcija za upload slike*/
		this.createImageFromBase64String(user.getImage(), path+"src/main/resources/static/pictures/"+user.getFirstName());
		user.setImage("pictures/"+user.getFirstName()+".png");
	}
	
	@Override
	public void createImageFromBase64String(String base64, String path){
		/*funkcija za upload slike*/
		String imageString = base64.split(",")[1]; 
		
		byte[] imageByte; 
		
		BASE64Decoder decoder = new BASE64Decoder(); 
		try {
			imageByte = decoder.decodeBuffer(imageString);
			ByteArrayInputStream bis = new ByteArrayInputStream(imageByte); 
			BufferedImage image = ImageIO.read(bis); 
			bis.close();
			System.out.println(path);
			File imageFile = new File(path+".png");
		
			ImageIO.write(image, "png",imageFile); 
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
	}
}
