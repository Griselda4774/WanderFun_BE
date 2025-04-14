package com.wanderfun.domainlayer.model.checkins;

import com.wanderfun.domainlayer.model.places.Place;

import java.time.LocalTime;

public class CheckIn {
    private Long id;
    private Long userId;
    private Place place;
    private LocalTime checkInTime;

    public CheckIn() {
    }
}
