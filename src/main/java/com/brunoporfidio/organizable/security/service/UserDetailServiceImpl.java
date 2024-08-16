package com.brunoporfidio.organizable.security.service;

import com.brunoporfidio.organizable.security.model.UserS;
import com.brunoporfidio.organizable.security.repository.IUserRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    
    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserS user = this.iUserRepository.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("User not Found.");
        }
        return new User(user.getUserName(), user.getPassword(), new ArrayList<>());
    }
    
    public UserS getUserByUsername(String username) {
        return this.iUserRepository.findByUserName(username);
    }
}
