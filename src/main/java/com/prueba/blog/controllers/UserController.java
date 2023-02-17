package com.prueba.blog.controllers;

import com.prueba.blog.dtos.requests.UserDTO;
import com.prueba.blog.dtos.responses.UserResponseDTO;
import com.prueba.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public @ResponseBody ResponseEntity<UserResponseDTO> getUser() {
        return ResponseEntity.ok(userService.getUser());
    }

    @PostMapping
    public @ResponseBody ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }
}
