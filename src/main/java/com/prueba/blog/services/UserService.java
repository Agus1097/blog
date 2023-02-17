package com.prueba.blog.services;

import com.prueba.blog.dtos.requests.UserDTO;
import com.prueba.blog.dtos.responses.UserResponseDTO;
import com.prueba.blog.exceptions.entities.UserAlreadyExistsException;
import com.prueba.blog.mappers.UserMapper;
import com.prueba.blog.models.User;
import com.prueba.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserResponseDTO createUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new UserAlreadyExistsException(String.format("Ya existe un usuario con mail: %s", userDTO.getEmail()));
        }
        User user = new User(UUID.randomUUID().toString(), userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(),
                passwordEncoder().encode(userDTO.getPassword()));
        userRepository.save(user);
        return userMapper.mapFromUser(user);
    }

    @Transactional(readOnly = true)
    public UserResponseDTO getUser() {
        String email = getEmailByAuthentication();
        return getUserByEmail(email);
    }

    @Transactional(readOnly = true)
    public UserResponseDTO getUserByEmail(String email) {
        if (!userRepository.existsByEmail(email)) {
            throw new UsernameNotFoundException(String.format("No existe el usuario con email %s", email));
        }
        User user = userRepository.findByEmail(email);
        return userMapper.mapFromUser(user);
    }

    @Transactional(readOnly = true)
    public User getUserByAuthentication() {
        String email = getEmailByAuthentication();
        if (!userRepository.existsByEmail(email)) {
            throw new UsernameNotFoundException(String.format("No existe el usuario con email %s", email));
        }
        return userRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public String getEmailByAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getPrincipal().toString();
    }

    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
