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
import com.brunoporfidio.organizable.security.jwt.JwtFilter;
import com.brunoporfidio.organizable.security.jwt.JwtUtil;
import com.brunoporfidio.organizable.security.service.UserDetailService;
import com.brunoporfidio.organizable.service.UserService;
import static java.lang.Math.log;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import org.apache.commons.validator.EmailValidator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private JwtUtil jwtUtil;

//    @PostMapping("/signup")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return new ResponseEntity(new Message("Campos mal puestos"), HttpStatus.BAD_REQUEST);
//        }
//
//        if (!EmailValidator.getInstance().isValid(userDTO.getEmail())) {
//            return new ResponseEntity<>(new Message("Email inválido"), HttpStatus.BAD_REQUEST);
//        }
//
//        User existingUser = userService.findByEmail(userDTO.getEmail());
//        if (existingUser != null) {
//            return new ResponseEntity<>(new Message("Email ya registrado"), HttpStatus.BAD_REQUEST);
//        }
//
//        User newUser = new User();
//        newUser.setCompanyName(userDTO.getCompanyName());
//        newUser.setName(userDTO.getName());
//        newUser.setLastname(userDTO.getLastname());
//        newUser.setEmail(userDTO.getEmail());
//        newUser.setPassword(userDTO.getPassword());
//        newUser.setRole("user");
//
//        userService.signUp(newUser);
//
//        return new ResponseEntity<>(new Message("User Registrated"), HttpStatus.CREATED);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return new ResponseEntity<>(new Message("Campos mal puestos: " + bindingResult.getAllErrors()), HttpStatus.BAD_REQUEST);
//        }
//
//        // Buscar el usuario en la base de datos por email
//        User user = userService.findByEmail(userDTO.getEmail());
//        if (user == null) {
//            return new ResponseEntity<>(new Message("Email o contraseña incorrectos"), HttpStatus.BAD_REQUEST);
//        }
//
//        try {
//            // Intentar autenticar al usuario
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            // Generar el token JWT si la autenticación es exitosa
//            String jwt = jwtUtil.generateToken(user.getEmail(), user.getRole());
//            return ResponseEntity.ok(new Message(jwt));
//
//        } catch (Exception e) {
//            // Captura y maneja la excepción si la autenticación falla
//            e.printStackTrace();
//            return new ResponseEntity<>(new Message("Email o contraseña incorrectos"), HttpStatus.BAD_REQUEST);
//        }
//    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> findAllUser() {
        List<User> user = userService.findAll();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable("email") String email) {

//        if (!userService.existByEmail(email)) {
//            return new ResponseEntity<>(new Message("No Exist Email"), HttpStatus.BAD_REQUEST);
//        }
        User user = userService.findByEmail(email);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {

        if (StringUtils.isBlank(userDTO.getCompanyName())) {
            return new ResponseEntity<>(new Message("Company Name Empty"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(userDTO.getEmail())) {
            return new ResponseEntity<>(new Message("Email Empty"), HttpStatus.BAD_REQUEST);
        }

        if (!EmailValidator.getInstance().isValid(userDTO.getEmail())) {
            return new ResponseEntity<>(new Message("Invalid Email"), HttpStatus.BAD_REQUEST);
        }

        if (userDTO.getEmail().equals(userDTO.getEmail())) {
            return new ResponseEntity<>(new Message("Email already exists"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(userDTO.getLastname())) {
            return new ResponseEntity<>(new Message("Last Name Empty"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(userDTO.getEmail())) {
            return new ResponseEntity<>(new Message("Email Empty"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(userDTO.getPassword())) {
            return new ResponseEntity<>(new Message("Password Empty"), HttpStatus.BAD_REQUEST);
        }

        User newUser = new User();
        newUser.setCompanyName(userDTO.getCompanyName());
        newUser.setUserName(userDTO.getUserName());
        newUser.setName(userDTO.getName());
        newUser.setLastname(userDTO.getLastname());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(userDTO.getPassword());

        userService.signUp(newUser);

        return new ResponseEntity<>(new Message("User Created"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> editUser(@PathVariable("id") Integer id, @RequestBody UserDTO userDTO) {
        if (!userService.existById(id)) {
            return new ResponseEntity<>(new Message("No exist"), HttpStatus.NOT_FOUND);
        }

        if (StringUtils.isBlank(userDTO.getCompanyName())) {
            return new ResponseEntity<>(new Message("Company Name Empty"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(userDTO.getName())) {
            return new ResponseEntity<>(new Message("Name Empty"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(userDTO.getLastname())) {
            return new ResponseEntity<>(new Message("Last Name Empty"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(userDTO.getEmail())) {
            return new ResponseEntity<>(new Message("Email Empty"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(userDTO.getPassword())) {
            return new ResponseEntity<>(new Message("Password Empty"), HttpStatus.BAD_REQUEST);
        }

        User editUser = userService.findById(id).get();
        editUser.setCompanyName(userDTO.getCompanyName());
        editUser.setUserName(userDTO.getUserName());
        editUser.setName(userDTO.getName());
        editUser.setLastname(userDTO.getLastname());
        editUser.setEmail(userDTO.getEmail());
        editUser.setPassword(userDTO.getPassword());

        userService.signUp(editUser);

        return new ResponseEntity<>(new Message("User Updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {
        if (!userService.existById(id)) {
            return new ResponseEntity<>(new Message("No Exist"), HttpStatus.BAD_REQUEST);
        }

        userService.deleteUser(id);

        return new ResponseEntity<>(new Message("User Deleted"), HttpStatus.OK);
    }

}
