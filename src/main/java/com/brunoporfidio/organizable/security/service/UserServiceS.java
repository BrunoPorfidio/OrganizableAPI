package com.brunoporfidio.organizable.security.service;

import com.brunoporfidio.organizable.security.model.UserS;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.brunoporfidio.organizable.security.repository.IUserRepository;

@Service
@Transactional
public class UserServiceS {

    @Autowired
    IUserRepository userRepository;
    
    public Optional<UserS>getByUserName(String userName){
		return userRepository.findByUserName(userName);
	}
	
	public boolean existsByUserName(String userName) {
		return userRepository.existsByUserName(userName);
	}

	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}
	
	public void save(UserS user){
		userRepository.save(user);
	}	
}
