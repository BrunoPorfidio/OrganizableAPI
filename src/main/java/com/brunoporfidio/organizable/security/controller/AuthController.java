package com.brunoporfidio.organizable.security.controller;

import com.brunoporfidio.organizable.security.DTO.AuthResponseDTO;
import com.brunoporfidio.organizable.security.DTO.JwtDTO;
import com.brunoporfidio.organizable.security.DTO.NewUser;
import com.brunoporfidio.organizable.security.DTO.UserLogin;
import com.brunoporfidio.organizable.security.model.UserS;
import com.brunoporfidio.organizable.security.model.Rol;
import com.brunoporfidio.organizable.security.enums.RolName;
import com.brunoporfidio.organizable.security.jwt.JwtUtil;
import com.brunoporfidio.organizable.security.repository.IUserRepository;
import com.brunoporfidio.organizable.security.service.RolService;
import com.brunoporfidio.organizable.security.service.UserServiceS;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.HashSet;
import java.util.Set;
import jakarta.validation.Valid;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserServiceS userService;

    @Autowired
    private RolService rolService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserDetailsService  userDetailsService;
    
    @Autowired
    private IUserRepository iUserRepository;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    

 @PostMapping("/signup")
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

    String encodedPassword = passwordEncoder.encode(newUser.getPassword());
    UserS user = new UserS(newUser.getUserName(), newUser.getName(), newUser.getEmail(), encodedPassword);

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
    public ResponseEntity<JwtDTO> login(@RequestBody UserLogin userLogin, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Message("Wrong login data"), HttpStatus.BAD_REQUEST);
        
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                 userLogin.getUserName(), userLogin.getPassword()
            ));

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userLogin.getUserName());
            
            UserS  userS = iUserRepository.findByUserName(userLogin.getUserName());
            
            String roles = userS.getRoles().stream().map(rol -> rol.getRolName().name()).collect(Collectors.joining(","));

            String jwt = this.jwtUtil.generateToken(userDetails, roles);
            String refreshToken = this.jwtUtil.generateRefreshToken(userDetails, roles);

            AuthResponseDTO authResponseDto = new AuthResponseDTO();
            authResponseDto.setToken(jwt);
            authResponseDto.setRefreshToken(refreshToken);
            
            JwtDTO jwtDTO = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
            
            return new ResponseEntity(jwtDTO, HttpStatus.OK);

        }catch (Exception e) {
              return (ResponseEntity<JwtDTO>) ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }
    }

}
