package com.prueba.blog.services;

import com.prueba.blog.dtos.requests.UserDTO;
import com.prueba.blog.dtos.responses.UserResponseDTO;
import com.prueba.blog.exceptions.entities.UserAlreadyExistsException;
import com.prueba.blog.mappers.UserMapper;
import com.prueba.blog.models.User;
import com.prueba.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        User user = userMapper.mapFromUserDTO(userDTO);
        user.setEncryptedPassword(passwordEncoder().encode(userDTO.getPassword()));
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException(String.format("Ya existe un usuario con el mail: %s", user.getEmail()));
        }
        userRepository.save(user);
        return userMapper.mapFromUser(user);
    }

    @Transactional
    public UserResponseDTO getUser(String email) {
        User user = userRepository.findByEmail(email);
        if (!userRepository.existsByEmail(email)) {
            throw new UsernameNotFoundException(String.format("El usuario con email %s no existe", email));
        }
        return userMapper.mapFromUser(user);
    }

    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
