package com.prueba.blog.dtos.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostDTO {

    @NotEmpty(message = "El título es obligatorio")
    private String title;

    @NotEmpty(message = "El contenido es obligatorio")
    private String content;

    @JsonProperty("expiration_time")
    @NotNull(message = "El tiempo de expiración es obligatorio")
    @Size(min = 1, max = 72, message = "El tiempo de expiración es invalido")
    private int expirationTime;

    @JsonProperty("exposure_id")
    @NotNull(message = "El tipo del post es obligatorio")
    @Size(min = 1, max = 2, message = "El tipo del post es invalido")
    private Long exposureId;

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

    public int getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(int expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Long getExposureId() {
        return exposureId;
    }

    public void setExposureId(Long exposureId) {
        this.exposureId = exposureId;
    }
}
