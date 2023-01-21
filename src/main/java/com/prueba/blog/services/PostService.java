package com.prueba.blog.services;

import com.prueba.blog.dtos.requests.PostDTO;
import com.prueba.blog.dtos.responses.PostResponseDTO;
import com.prueba.blog.dtos.responses.UserResponseDTO;
import com.prueba.blog.exceptions.entities.IllegalUserException;
import com.prueba.blog.mappers.PostMapper;
import com.prueba.blog.models.Exposure;
import com.prueba.blog.models.Post;
import com.prueba.blog.models.User;
import com.prueba.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class PostService {

    private PostRepository postRepository;
    private PostMapper postMapper;
    private UserService userService;
    private ExposureService exposureService;

    private static final Long PUBLIC = 2L;
    private static final String PRIVATE = "Private";

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
        User user = userService.getUserByAuthentication();
        Exposure exposure = exposureService.getById(postDTO.getExposureId());
        Post post = postMapper.mapFromPostDTO(postDTO, exposure, user);
        postRepository.save(post);

        return postMapper.mapFromPost(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponseDTO> getPostsByUser() {
        String email = userService.getEmailByAuthentication();
        List<Post> posts = postRepository.findAllByUser_Email(email);
        return postMapper.mapFromPost(posts);
    }

    @Transactional(readOnly = true)
    public List<PostResponseDTO> getPublicPosts() {
        List<Post> posts = postRepository.getPublicPosts(PUBLIC, Instant.now());
        return postMapper.mapFromPost(posts);
    }

    @Transactional(readOnly = true)
    public PostResponseDTO getPostById(String postId) {
        Post post = postRepository.findByPostId(postId);
        PostResponseDTO postResponseDTO = postMapper.mapFromPost(post);

        if (postResponseDTO.getExposure().getType().equals(PRIVATE) || postResponseDTO.isExpired()) {
            UserResponseDTO userResponseDTO = userService.getUser();
            if (userResponseDTO == null || !userResponseDTO.getUserId().equals(postResponseDTO.getUser().getUserId())) {
                throw new IllegalUserException("No tienes permisos para realizar esta acción");
            }
        }

        return postResponseDTO;
    }

    @Transactional
    public void deletePost(String postId) {
        Post post = postRepository.findByPostId(postId);
        UserResponseDTO userResponseDTO = userService.getUser();

        if (!post.getUser().getUserId().equals(userResponseDTO.getUserId())) {
            throw new IllegalUserException("No tienes permisos para realizar esta acción");
        }

        postRepository.delete(post);
    }

    @Transactional
    public PostResponseDTO updatePost(String postId, PostDTO postDTO) {
        UserResponseDTO userResponseDTO = userService.getUser();
        Post post = postRepository.findByPostId(postId);

        if (!post.getUser().getUserId().equals(userResponseDTO.getUserId())) {
            throw new IllegalUserException("No tienes permisos para realizar esta acción");
        }

        Exposure exposure = exposureService.getById(postDTO.getExposureId());
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setExpirationDate(Instant.now().plusMillis(postDTO.getExpirationDate() * 3600000L));
        post.setExposure(exposure);
        postRepository.save(post);

        return postMapper.mapFromPost(post);
    }
}
