package com.wanderfun.infrastructurelayer.persistence.entity.checkins;

import com.wanderfun.infrastructurelayer.persistence.entity.places.Place;

import java.time.LocalTime;

public class CheckIn {
    private Long id;
    private Long userId;
    private Place place;
    private LocalTime checkInTime;

    public CheckIn() {
    }
}
