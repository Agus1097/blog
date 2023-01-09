package com.prueba.blog.mappers;

import com.prueba.blog.dtos.responses.UserResponseDTO;
import com.prueba.blog.models.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserResponseDTO mapFromUser(User user);
}
