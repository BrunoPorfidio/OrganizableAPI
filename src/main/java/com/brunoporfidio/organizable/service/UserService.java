package com.brunoporfidio.organizable.service;

import com.brunoporfidio.organizable.model.User;
import com.brunoporfidio.organizable.repository.UserRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class UserService{
    
    @Autowired
    private final UserRepository userRepository;
    
    @Autowired
    public UserService( UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public User signUp(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    public User login(User user){
        return userRepository.save(user);
    }
    
    public List<User> findAll(){
        return (List<User>) userRepository.findAll();
    }
    
    public Optional<User> findById(Integer id){
        return userRepository.findById(id);
    }
    
    public boolean existById(Integer id){
        return userRepository.existsById(id);
    }
    
     public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    
    public User existByEmail(String email){
        return userRepository.findByEmail(email);
    }
    
    public User editUser(User user){
        return userRepository.save(user);
    }
    
    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }

}
