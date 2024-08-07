//package com.brunoporfidio.organizable.service;
//
//import com.brunoporfidio.organizable.dto.Message;
//import com.brunoporfidio.organizable.model.User;
//import com.brunoporfidio.organizable.repository.UserRepository;
//import com.brunoporfidio.organizable.security.jwt.JwtUtil;
//import com.brunoporfidio.organizable.security.service.UserDetailService;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//import java.util.Optional;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserDetailService userDetailService;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public ResponseEntity<String> login(Map<String, String> requestMap) {
//        try {
//            User user = userRepository.findByEmail(requestMap.get("email"));
//            if (user != null && passwordEncoder.matches(requestMap.get("password"), user.getPassword())) {
//                // Realiza la autenticación
//                Authentication authentication = authenticationManager.authenticate(
//                        new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password"))
//                );
////                if (authentication.isAuthenticated()) {
////                    // Verifica el estado del usuario y genera el token JWT
////                    if (userDetailService.getUserDetail().getStatus().equalsIgnoreCase("true")) {
////                        return new ResponseEntity<>("{\"token\":\"" + jwtUtil
////                                .generateToken(userDetailService.getUserDetail().getEmail(), userDetailService.getUserDetail().getRole()) + "\"}",
////                                HttpStatus.OK);
////                    } else {
////                        return new ResponseEntity<>("{\"Message\":\" Wait Admin Approval \"}", HttpStatus.BAD_REQUEST);
////                    }
////                }
//            }
//        } catch (Exception exception) {
//            log.error("Error during login process", exception);
//        }
//        return new ResponseEntity<>("{\"Message\":\" Wrong Credentials \"}", HttpStatus.BAD_REQUEST);
//    }
//
//    @Override
//    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
//        try {
//            if (validateSingupMap(requestMap)) {
//                User user = userRepository.findByEmail(requestMap.get("email"));
//                if (Objects.isNull(user)) {
//                    userRepository.save(getUserFromMap(requestMap));
//                    return Message.getResponseEnity("User Created", HttpStatus.CREATED);
//                } else {
//                    return Message.getResponseEnity("Email Already Exist.", HttpStatus.BAD_REQUEST);
//                }
//            } else {
//                return Message.getResponseEnity("Invalid Data.", HttpStatus.BAD_REQUEST);
//            }
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//        return Message.getResponseEnity("Somthing went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    private Boolean validateSingupMap(Map<String, String> requestMap) {
//        if (requestMap.containsKey("companyName") && requestMap.containsKey("name")
//                && requestMap.containsKey("lastname") && requestMap.containsKey("email")
//                && requestMap.containsKey("password")) {
//            return true;
//        }
//        return false;
//    }
//
//    private User getUserFromMap(Map<String, String> requestMap) {
//        User user = new User();
//        user.setCompanyName(requestMap.get("companyName"));
//        user.setName(requestMap.get("name"));
//        user.setLastname(requestMap.get("lastname"));
//        user.setEmail(requestMap.get("email"));
//        user.setPassword(passwordEncoder.encode(requestMap.get("password")));
//        user.setRole("user");
//        user.setStatus("false");
//        return user;
//    }
//
//    @Override
//    public List<User> getAllUsers() {
//        return (List<User>) userRepository.findAll();
//    }
//
//    @Override
//    public Optional<User> getUserById(Integer id) {
//        return userRepository.findById(id);
//    }
//
//    @Override
//    public User saveUser(User user) {
//        return userRepository.save(user);
//    }
//}
