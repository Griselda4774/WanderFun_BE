package com.wanderfun.infrastructurelayer.persistence.entity.posts;

import com.wanderfun.infrastructurelayer.persistence.entity.users.User;

public class Like {
    private Long id;
    private User user;
    private Long targetId;
    private LikeTargetType targetType;

    public Like() {
    }
}
