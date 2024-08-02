package com.brunoporfidio.organizable.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.brunoporfidio.organizable.dto.Message;
import com.brunoporfidio.organizable.dto.UserDTO;
import com.brunoporfidio.organizable.model.User;
import com.brunoporfidio.organizable.service.UserService;
import org.apache.commons.validator.EmailValidator;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/all")
    public ResponseEntity<List<User>> findAllUser(){
        List<User>  user = userService.findAll();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    @PostMapping("/add")
    public ResponseEntity<?> createUser (@RequestBody UserDTO userDTO){
     
         if ( StringUtils.isBlank( userDTO.getCompanyName() ) )
            return new ResponseEntity<>( new Message("Company Name Empty"), HttpStatus.BAD_REQUEST );
         
         if (StringUtils.isBlank(userDTO.getEmail()))
            return new ResponseEntity<>(new Message("Email Empty"), HttpStatus.BAD_REQUEST);

        if (!EmailValidator.getInstance().isValid(userDTO.getEmail()))
            return new ResponseEntity<>(new Message("Invalid Email"), HttpStatus.BAD_REQUEST);

        if (userService.existByEmail(userDTO.getEmail()))
            return new ResponseEntity<>(new Message("Email already exists"), HttpStatus.BAD_REQUEST);

        if ( StringUtils.isBlank( userDTO.getLastname() ) )
            return new ResponseEntity<>( new Message("Last Name Empty"), HttpStatus.BAD_REQUEST );

        if ( StringUtils.isBlank( userDTO.getEmail() ) )
            return new ResponseEntity<>( new Message("Email Empty"), HttpStatus.BAD_REQUEST );
        
        if ( StringUtils.isBlank( userDTO.getPassword() ) )
            return new ResponseEntity<>( new Message("Password Empty"), HttpStatus.BAD_REQUEST );
        
        User newUser = new User();
        newUser.setCompanyName(userDTO.getCompanyName());
        newUser.setName(userDTO.getName());
        newUser.setLastname(userDTO.getLastname());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(userDTO.getPassword());
        
        userService.createUser(newUser);
        
        return new ResponseEntity<>( new Message("User Created"), HttpStatus.CREATED);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?>  editUser(@PathVariable("id") Long id, @RequestBody UserDTO userDTO){
        if (!userService.existById(id))
            return new ResponseEntity<>(new Message("No exist"), HttpStatus.NOT_FOUND);
        
        if (StringUtils.isBlank(userDTO.getCompanyName()))
            return new ResponseEntity<>(new Message("Company Name Empty"), HttpStatus.BAD_REQUEST);
        
        if (StringUtils.isBlank(userDTO.getName()))
            return new ResponseEntity<>(new Message("Name Empty"), HttpStatus.BAD_REQUEST);
        
        if (StringUtils.isBlank(userDTO.getLastname()))
            return new ResponseEntity<>(new Message("Last Name Empty"), HttpStatus.BAD_REQUEST);
        
        if (StringUtils.isBlank(userDTO.getEmail()))
            return new ResponseEntity<>(new Message("Email Empty"), HttpStatus.BAD_REQUEST);
        
        if (StringUtils.isBlank(userDTO.getPassword()))
            return new ResponseEntity<>(new Message("Password Empty"), HttpStatus.BAD_REQUEST);
        
        User editUser =  userService.findById(id).get();
        editUser.setCompanyName(userDTO.getCompanyName());
        editUser.setName(userDTO.getName());
        editUser.setLastname(userDTO.getLastname());
        editUser.setEmail(userDTO.getEmail());
        editUser.setPassword(userDTO.getPassword());
        
        userService.createUser(editUser);
        
        return new ResponseEntity<>( new Message("User Updated"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser( @PathVariable("id")Long id){
        if( !userService.existById(id))
            return new ResponseEntity<>(new Message("No Exist"),  HttpStatus.BAD_REQUEST);
        
        userService.deleteUser(id);
        
        return new ResponseEntity<>( new Message("User Deleted"), HttpStatus.OK);
    }
    
}
