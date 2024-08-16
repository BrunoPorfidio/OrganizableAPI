package com.brunoporfidio.organizable.controller;

import com.brunoporfidio.organizable.dto.Message;
import com.brunoporfidio.organizable.dto.UserDTO;
import com.brunoporfidio.organizable.model.User;
import com.brunoporfidio.organizable.security.model.UserS;
import com.brunoporfidio.organizable.security.service.UserSecurityService;
import com.brunoporfidio.organizable.security.service.UserServiceS;
import com.brunoporfidio.organizable.service.UserService;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.validator.EmailValidator;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserServiceS userserviceS; 
    
    @Autowired
    private UserSecurityService userSecurityService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<UserS>> getAllUsers() {
        List<UserS> users = userserviceS.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable("email") String email) {

        if (!userService.existsByEmail(email)) {
            return new ResponseEntity<>(new Message("No Exist Email"), HttpStatus.BAD_REQUEST);
        }
        User user = userService.findByEmail(email);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {

        if (StringUtils.isBlank(userDTO.getEmail())) {
            return new ResponseEntity<>(new Message("Email Empty"), HttpStatus.BAD_REQUEST);
        }

        if (!EmailValidator.getInstance().isValid(userDTO.getEmail())) {
            return new ResponseEntity<>(new Message("Invalid Email"), HttpStatus.BAD_REQUEST);
        }

        if (userDTO.getEmail().equals(userDTO.getEmail())) {
            return new ResponseEntity<>(new Message("Email already exists"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(userDTO.getEmail())) {
            return new ResponseEntity<>(new Message("Email Empty"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(userDTO.getPassword())) {
            return new ResponseEntity<>(new Message("Password Empty"), HttpStatus.BAD_REQUEST);
        }

        User newUser = new User();
        newUser.setUserName(userDTO.getUserName());
        newUser.setName(userDTO.getName());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(userDTO.getPassword());

        userService.signUp(newUser);

        return new ResponseEntity<>(new Message("User Created"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> editUser(@PathVariable("id") Integer id, @RequestBody UserDTO userDTO) {
                
        if (!userSecurityService.isUserOrAdmin(id)) {
            return new ResponseEntity<>(new Message("Not authorized"), HttpStatus.FORBIDDEN);
        }
        
        if (!userService.existById(id)) {
            return new ResponseEntity<>(new Message("No exist"), HttpStatus.NOT_FOUND);
        }

        if (StringUtils.isBlank(userDTO.getEmail())) {
            return new ResponseEntity<>(new Message("Email Empty"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(userDTO.getPassword())) {
            return new ResponseEntity<>(new Message("Password Empty"), HttpStatus.BAD_REQUEST);
        }

        User editUser = userService.findById(id).get();
        editUser.setUserName(userDTO.getUserName());
        editUser.setName(userDTO.getName());
        editUser.setEmail(userDTO.getEmail());
        editUser.setPassword(userDTO.getPassword());

        userService.signUp(editUser);

        return new ResponseEntity<>(new Message("User Updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {
                
        if (!userSecurityService.isUserOrAdmin(id)) {
            return new ResponseEntity<>(new Message("Not authorized"), HttpStatus.FORBIDDEN);
        }
        
        if (!userService.existById(id)) {
            return new ResponseEntity<>(new Message("No Exist"), HttpStatus.BAD_REQUEST);
        }

        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(new Message("User Deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Message("Error deleting user: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
