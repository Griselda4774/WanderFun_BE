package com.wanderfun.domainlayer.model.places;

import com.wanderfun.domainlayer.model.users.User;

import java.time.LocalTime;

public class Feedback {
    private Long id;
    private User user;
    private float rating;
    private String comment;
    private LocalTime createAt;
    private Long placeDetailId;

    public Feedback() {
    }
}
