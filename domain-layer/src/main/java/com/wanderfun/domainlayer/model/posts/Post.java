package com.wanderfun.domainlayer.model.posts;

import com.wanderfun.domainlayer.model.places.Place;
import com.wanderfun.domainlayer.model.users.User;
import com.wanderfun.domainlayer.model.trips.Trip;

import java.time.LocalTime;

public class Post {
    private Long id;
    private User author;
    private String content;
    private LocalTime createAt;
    private LocalTime updateAt;
    private Place place;
    private boolean isTripShare;
    private Trip trip;
    private int like;

    public Post() {
    }
}
