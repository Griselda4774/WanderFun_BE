package com.wanderfun.domainlayer.model.places;

import com.wanderfun.domainlayer.model.users.User;

import java.time.LocalDateTime;

public class Feedback {
    private Long id;
    private User user;
    private float rating;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Long placeDetailId;

    public Feedback() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public Long getPlaceDetailId() {
        return placeDetailId;
    }

    public void setPlaceDetailId(Long placeDetailId) {
        this.placeDetailId = placeDetailId;
    }
}
