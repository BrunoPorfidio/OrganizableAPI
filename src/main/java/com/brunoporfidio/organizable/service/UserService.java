package com.brunoporfidio.organizable.service;

import com.brunoporfidio.organizable.model.User;
import com.brunoporfidio.organizable.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService{
    
    private final UserRepository userRepository;
    
    @Autowired
    public UserService( UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    public User createUser(User user){
        return userRepository.save(user);
    }
    
    public List<User> findAll(){
        return (List<User>) userRepository.findAll();
    }
    
    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }
    
    public boolean existById(Long id){
        return userRepository.existsById(id);
    }
    
    public boolean existByEmail(String email){
        return userRepository.findByEmail(email).isPresent();
    }
    
    public User editUser(User user){
        return userRepository.save(user);
    }
    
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
