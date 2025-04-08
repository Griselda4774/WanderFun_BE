package com.project2.wanderfun.domain.model.posts;

import com.project2.wanderfun.domain.model.users.User;

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
