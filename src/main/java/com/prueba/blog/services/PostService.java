package com.prueba.blog.services;

import com.prueba.blog.dtos.requests.PostDTO;
import com.prueba.blog.dtos.responses.PostResponseDTO;
import com.prueba.blog.mappers.PostMapper;
import com.prueba.blog.models.Exposure;
import com.prueba.blog.models.Post;
import com.prueba.blog.models.User;
import com.prueba.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
public class PostService {

    private PostRepository postRepository;
    private PostMapper postMapper;
    private UserService userService;
    private ExposureService exposureService;

    @Autowired
    public PostService(PostRepository postRepository,
                       PostMapper postMapper,
                       UserService userService,
                       ExposureService exposureService) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.userService = userService;
        this.exposureService = exposureService;
    }

    @Transactional
    public PostResponseDTO createPost(PostDTO postDTO) {

        Instant expirationDate = Instant.now().plusMillis(postDTO.getExpirationDate());
        String email = userService.getEmailByAuthentication();

        User user = userService.getUserByEmail(email);
        Exposure exposure = exposureService.getById(postDTO.getExposureId());
        Post post = new Post(UUID.randomUUID().toString(), postDTO.getTitle(), postDTO.getContent(), expirationDate, user, exposure);
        postRepository.save(post);

        return postMapper.mapFromPost(post);
    }
}
