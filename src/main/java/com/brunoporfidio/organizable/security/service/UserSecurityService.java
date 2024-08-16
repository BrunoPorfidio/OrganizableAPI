package com.brunoporfidio.organizable.security.service;
import com.brunoporfidio.organizable.security.model.UserS;
import com.brunoporfidio.organizable.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    public boolean isUserOrAdmin(Integer userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        UserS user = userDetailService.getUserByUsername(username);
        return user.getId().equals(userId) || user.getRoles().stream().anyMatch(role -> role.getRolName().name().equals("ROLE_ADMIN"));
    }
    
}
