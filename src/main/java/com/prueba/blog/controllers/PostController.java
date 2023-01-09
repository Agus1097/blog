package com.prueba.blog.controllers;

import com.prueba.blog.dtos.requests.PostDTO;
import com.prueba.blog.dtos.responses.PostResponseDTO;
import com.prueba.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public @ResponseBody ResponseEntity<PostResponseDTO> createPost(@RequestBody PostDTO postDTO) {
        return ResponseEntity.ok(postService.createPost(postDTO));
    }
}
