package com.prueba.blog.mappers;

import com.prueba.blog.dtos.responses.PostResponseDTO;
import com.prueba.blog.models.Post;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(uses = {
        UserMapper.class,
        ExposureMapper.class
}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PostMapper {

    PostResponseDTO mapFromPost(Post post);
}
