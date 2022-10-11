package com.revature.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.models.User;
import com.revature.repositories.UserRepository;

@Service
public class ResetPassService {
	
	private final UserRepository userRepository;
	
	public ResetPassService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
    public Optional<User> findByEmail(String email){
    	return userRepository.findByEmail(email);
    }
    
    public User replace(User user) {
    	return userRepository.saveAndFlush(user);
    }
    
 public User resetPassword(String email, String newPassword ) {
    	
    	User user = this.findByEmail(email).get();
    	user.setPassword(newPassword);
    	
    	return this.replace(user);
    }
    

}
