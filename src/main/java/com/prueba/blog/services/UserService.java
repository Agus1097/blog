package com.prueba.blog.services;

import com.prueba.blog.dtos.requests.UserDTO;
import com.prueba.blog.dtos.responses.UserResponseDTO;
import com.prueba.blog.exceptions.entities.UserAlreadyExistsException;
import com.prueba.blog.mappers.UserMapper;
import com.prueba.blog.models.User;
import com.prueba.blog.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserResponseDTO createUser(UserDTO userDTO) {
        User user = userMapper.mapFromUserDTO(userDTO);
        UUID userId = UUID.randomUUID();
        user.setUserId(userId.toString());
        try {
            userRepository.save(user);
            return userMapper.mapFromUser(user);
        } catch (Exception ex) {
            log.error("UserService#createUser: E: {}.", ex.getMessage());
            throw new UserAlreadyExistsException(String.format("Ya existe un usuario con el mail: %s", user.getEmail()));
        }
    }
}
