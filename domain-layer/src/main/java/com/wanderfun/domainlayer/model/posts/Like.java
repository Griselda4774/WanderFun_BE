package com.wanderfun.domainlayer.model.posts;

import com.wanderfun.domainlayer.model.users.User;

public class Like {
    private Long id;
    private User author;
    private Long targetId;
    private LikeTargetType targetType;

    public Like() {
    }
}
