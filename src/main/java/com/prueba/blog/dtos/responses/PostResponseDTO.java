package com.prueba.blog.dtos.responses;

import com.prueba.blog.dtos.requests.ExposureDTO;

import java.time.Instant;

public class PostResponseDTO {

    private String postId;

    private String title;

    private String content;

    private Instant createdAt;

    private Instant expirationDate;

    private UserResponseDTO user;

    private ExposureDTO exposure;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Instant expirationDate) {
        this.expirationDate = expirationDate;
    }

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
    }

    public ExposureDTO getExposure() {
        return exposure;
    }

    public void setExposure(ExposureDTO exposure) {
        this.exposure = exposure;
    }
}
