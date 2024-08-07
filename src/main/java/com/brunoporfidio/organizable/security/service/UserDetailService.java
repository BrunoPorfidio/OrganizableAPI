package com.brunoporfidio.organizable.security.service;

import com.brunoporfidio.organizable.model.User;
import com.brunoporfidio.organizable.repository.UserRepository;
import com.brunoporfidio.organizable.security.model.MainUser;
import com.brunoporfidio.organizable.security.model.UserS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserServiceS userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserS user = userService.getByUserName(userName).get();
        return MainUser.build(user);
//        User user = userRepository.findByEmail(email);
//        if (user == null) {
//            throw new UsernameNotFoundException("User Not Found");
//        }
//
//        return org.springframework.security.core.userdetails.User.builder()
//                .username(user.getEmail())
//                .password(user.getPassword())
//                .build();
    }
}
