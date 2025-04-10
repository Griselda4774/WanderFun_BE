package com.wanderfun.infrastructurelayer.persistence.entity.posts;

import com.wanderfun.infrastructurelayer.persistence.entity.trips.Trip;
import com.wanderfun.infrastructurelayer.persistence.entity.users.User;

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
