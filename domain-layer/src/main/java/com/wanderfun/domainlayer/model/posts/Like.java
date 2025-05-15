package com.wanderfun.domainlayer.model.posts;

import com.wanderfun.domainlayer.model.users.User;

public class Like {
    private Long id;
    private User user;
    private Long targetId;
    private LikeTargetType targetType;

    public Like() {
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

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public LikeTargetType getTargetType() {
        return targetType;
    }

    public void setTargetType(LikeTargetType targetType) {
        this.targetType = targetType;
    }
}
