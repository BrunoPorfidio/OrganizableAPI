package com.brunoporfidio.organizable.security.service;

import com.brunoporfidio.organizable.security.model.UserS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.brunoporfidio.organizable.security.repository.IUserRepository;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceS {

    @Autowired
    IUserRepository userRepository;

    public List<UserS> getAllUsers() {
        return (List<UserS>) userRepository.findAll();
    }
    
     public Optional<UserS> findById(Long id) {
        return userRepository.findById(id);
    }

    public UserS getByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public boolean existsByUserName(String userName) {
        return userRepository.existsByUserName(userName);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void save(UserS user) {
        userRepository.save(user);
    }
}
