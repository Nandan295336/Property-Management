package com.mycompany.property_management.controller;

import com.mycompany.property_management.dto.UserDTO;
import com.mycompany.property_management.service.impl.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User-Controller", description = "user registration and login")
public class UserController {

    @Autowired
    private UserService userService;

    //@ApiOperation(value = "register", notes = "This is used for user registration")
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO userDTO) {
        userDTO = userService.register(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PostMapping(path= "/login", produces = {"application/json"})
    public ResponseEntity<UserDTO> login(@Valid @RequestParam String Email, @RequestParam String Password) {
       // userDTO = userService.login(userDTO.getEmail(), userDTO.getPassword());
        UserDTO userDTO=null;
        userDTO = userService.login(Email, Password);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}