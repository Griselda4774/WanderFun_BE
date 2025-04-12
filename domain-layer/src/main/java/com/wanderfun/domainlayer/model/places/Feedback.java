package com.wanderfun.domainlayer.model.places;

import com.wanderfun.domainlayer.model.users.User;

import java.time.LocalTime;

public class Feedback {
    private Long id;
    private User author;
    private float rating;
    private String content;
    private LocalTime createAt;
    private LocalTime updateAt;
    private Long placeDetailId;

    public Feedback() {
    }
}
