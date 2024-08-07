package com.brunoporfidio.organizable.security.controller;

import com.brunoporfidio.organizable.security.DTO.JwtDTO;
import com.brunoporfidio.organizable.security.DTO.NewUser;
import com.brunoporfidio.organizable.security.DTO.UserLogin;
import com.brunoporfidio.organizable.security.model.UserS;
import com.brunoporfidio.organizable.security.model.Rol;
import com.brunoporfidio.organizable.security.enums.RolName;
import com.brunoporfidio.organizable.security.jwt.JwtUtil;
import com.brunoporfidio.organizable.security.service.RolService;
import com.brunoporfidio.organizable.security.service.UserServiceS;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserServiceS userService;

    @Autowired
    RolService rolService;
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/new")
    public ResponseEntity<?> registerUser(@Valid @RequestBody NewUser newUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new Message("Wrong Data, or Wrong Email."), HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByUserName(newUser.getUserName())) {
            return new ResponseEntity<>(new Message("UserName Already Exists."), HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByEmail(newUser.getEmail())) {
            return new ResponseEntity<>(new Message("Email Already Exists."), HttpStatus.BAD_REQUEST);
        }
        

        UserS user = new UserS(newUser.getUserName(), newUser.getName(), newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()));

        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRoleName(RolName.ROLE_USER).get());

        if (newUser.getRoles().contains("admin")) {
            roles.add(rolService.getByRoleName(RolName.ROLE_ADMIN).get());
        }
        user.setRoles(roles);
        userService.save(user);

        return new ResponseEntity<>(new Message("User Saved"), HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody UserLogin userLogin, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return  new ResponseEntity(new Message("Wrong data."), HttpStatus.BAD_REQUEST);
            
            Authentication authentication = authenticationManager.setAuthentication(new UsernamePasswordAuthenticationToken(
            UserLogin.getUserName(), UserLogin.getPassword())); 
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            String jwt = jwtUtil.generateToken(authentication);
        }
    }
}
