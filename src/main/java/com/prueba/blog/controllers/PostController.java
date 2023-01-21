package com.prueba.blog.controllers;

import com.prueba.blog.dtos.requests.PostDTO;
import com.prueba.blog.dtos.responses.PostResponseDTO;
import com.prueba.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public @ResponseBody ResponseEntity<List<PostResponseDTO>> getPostByUser() {
        return ResponseEntity.ok(postService.getPostsByUser());
    }

    @GetMapping("/public")
    public @ResponseBody ResponseEntity<List<PostResponseDTO>> getPublicPosts() {
        return ResponseEntity.ok(postService.getPublicPosts());
    }

    @GetMapping(path = "/{post_id}")
    public @ResponseBody ResponseEntity<PostResponseDTO> getPost(@PathVariable("post_id") String postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @DeleteMapping("/{post_id}")
    public void deletePost(@PathVariable("post_id") String postId) {
        postService.deletePost(postId);
    }

    @PutMapping("/{post_id}")
    public @ResponseBody ResponseEntity<PostResponseDTO> updatePost(@PathVariable("post_id") String postId,
                                                                    @RequestBody PostDTO postDTO) {
        return ResponseEntity.ok(postService.updatePost(postId, postDTO));
    }
}
