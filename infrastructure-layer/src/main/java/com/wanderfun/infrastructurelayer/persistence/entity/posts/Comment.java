package com.wanderfun.infrastructurelayer.persistence.entity.posts;

import com.wanderfun.infrastructurelayer.persistence.entity.users.User;

import java.time.LocalTime;

public class Comment {
    private Long id;
    private User user;
    private String content;
    private LocalTime createAt;
    private Long postId;
    private Long parentId;
    private int like;

    public Comment() {
    }
}
