package com.wanderfun.infrastructurelayer.persistence.entity.places;

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
