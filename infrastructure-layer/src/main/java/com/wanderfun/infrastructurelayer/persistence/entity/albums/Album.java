package com.wanderfun.infrastructurelayer.persistence.entity.albums;

import com.wanderfun.infrastructurelayer.persistence.entity.places.Place;

import java.time.LocalTime;

public class Album {
    private Long id;
    private String name;
    private String description;
    private LocalTime updatedAt;
    private Place place;
    private Long userId;

    public Album() {
    }
}
