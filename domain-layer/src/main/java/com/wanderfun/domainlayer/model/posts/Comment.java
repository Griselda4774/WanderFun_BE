package com.wanderfun.domainlayer.model.posts;

import com.wanderfun.domainlayer.model.users.User;

import java.time.LocalTime;

public class Comment {
    private Long id;
    private User author;
    private String content;
    private LocalTime createAt;
    private LocalTime updateAt;
    private Long postId;
    private Long parentId;
    private int like;

    public Comment() {
    }
}
