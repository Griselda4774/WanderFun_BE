package com.project2.wanderfun.domain.model.users;

import com.project2.wanderfun.domain.model.places.Place;

import java.time.LocalTime;

public class CheckIn {
    private Long id;
    private Long userId;
    private Place place;
    private LocalTime checkInTime;

    public CheckIn() {
    }
}
