package com.prueba.blog.services;

import com.prueba.blog.dtos.requests.UserDTO;
import com.prueba.blog.dtos.responses.UserResponseDTO;
import com.prueba.blog.exceptions.entities.UserAlreadyExistsException;
import com.prueba.blog.mappers.UserMapper;
import com.prueba.blog.models.UserModel;
import com.prueba.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       UserMapper userMapper,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    public UserResponseDTO createUser(UserDTO userDTO) {
        UserModel user = userMapper.mapFromUserDTO(userDTO);
        user.setEncryptedPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException(String.format("Ya existe un usuario con el mail: %s", user.getEmail()));
        }
        userRepository.save(user);
        return userMapper.mapFromUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByEmail(email);
        if (!userRepository.existsByEmail(email)) {
            throw new UsernameNotFoundException(email);
        }
        return new User(userModel.getEmail(), userModel.getEncryptedPassword(), new ArrayList<>());
    }
}
