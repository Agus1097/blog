package com.prueba.blog.mappers;

import com.prueba.blog.dtos.requests.PostDTO;
import com.prueba.blog.dtos.responses.PostResponseDTO;
import com.prueba.blog.models.Exposure;
import com.prueba.blog.models.Post;
import com.prueba.blog.models.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {
        UserMapper.class,
        ExposureMapper.class
}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PostMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "postId", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "expirationDate", expression = "java(java.time.Instant.now().plusMillis(postDTO.getExpirationTime() * 3600000L))")
    Post mapFromPostDTO(PostDTO postDTO, Exposure exposure, User user);

    @Mapping(target = "expired", expression = "java(java.time.Instant.now().isAfter(post.getExpirationDate()))")
    PostResponseDTO mapFromPost(Post post);

    List<PostResponseDTO> mapFromPost(List<Post> posts);
}
