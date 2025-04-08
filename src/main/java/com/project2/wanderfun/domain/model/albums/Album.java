package com.project2.wanderfun.domain.model.albums;

import com.project2.wanderfun.domain.model.places.Place;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

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
