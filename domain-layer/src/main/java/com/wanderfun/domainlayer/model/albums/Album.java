package com.wanderfun.domainlayer.model.albums;

import com.wanderfun.domainlayer.model.places.Place;

import java.time.LocalTime;

public class Album {
    private Long id;
    private String name;
    private String description;
    private LocalTime createdAt;
    private LocalTime updatedAt;
    private Place place;
    private Long userId;

    public Album() {
    }
}
