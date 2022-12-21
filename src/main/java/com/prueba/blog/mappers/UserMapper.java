package com.prueba.blog.mappers;

import com.prueba.blog.dtos.requests.UserDTO;
import com.prueba.blog.dtos.responses.UserResponseDTO;
import com.prueba.blog.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    @Mapping(source = "password", target = "encryptedPassword")
    User mapFromUserDTO(UserDTO userDTO);

    UserResponseDTO mapFromUser(User user);
}
