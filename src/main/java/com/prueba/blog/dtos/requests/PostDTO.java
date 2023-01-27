package com.prueba.blog.dtos.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostDTO {

    private String title;

    private String content;

    @JsonProperty("expiration_date")
    private int expirationDate;

    @JsonProperty("exposure_id")
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

    public int getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(int expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Long getExposureId() {
        return exposureId;
    }

    public void setExposureId(Long exposureId) {
        this.exposureId = exposureId;
    }
}
