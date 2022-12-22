package com.prueba.blog.mappers;

import com.prueba.blog.dtos.requests.UserDTO;
import com.prueba.blog.dtos.responses.UserResponseDTO;
import com.prueba.blog.models.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    @Mapping(target = "userId", expression = "java(java.util.UUID.randomUUID().toString())")
    UserModel mapFromUserDTO(UserDTO userDTO);

    UserResponseDTO mapFromUser(UserModel userModel);
}
