package com.project2.wanderfun.domain.model.posts;

import com.project2.wanderfun.domain.model.users.User;
import com.project2.wanderfun.domain.model.trips.Trip;

import java.time.LocalTime;

public class Post {
    private Long id;
    private User user;
    private String content;
    private LocalTime createAt;
    private boolean isTripShare;
    private Trip trip;
    private int like;

    public Post() {
    }
}
