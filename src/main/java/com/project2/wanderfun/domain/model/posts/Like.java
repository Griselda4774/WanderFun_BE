package com.project2.wanderfun.domain.model.posts;

import com.project2.wanderfun.domain.model.users.User;

public class Like {
    private Long id;
    private User user;
    private Long targetId;
    private LikeTargetType targetType;

    public Like() {
    }
}
