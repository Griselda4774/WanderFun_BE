package com.wanderfun.domainlayer.model.posts;

import com.wanderfun.domainlayer.model.users.User;
import com.wanderfun.domainlayer.model.trips.Trip;

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
