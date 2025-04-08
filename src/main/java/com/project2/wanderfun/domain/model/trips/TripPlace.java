package com.project2.wanderfun.domain.model.trips;

import com.project2.wanderfun.domain.model.places.Place;

import java.util.Date;

public class TripPlace {
    private Long id;
    private Place place;
    private Long tripId;
    private Date startTime;
    private Date endTime;

    public TripPlace() {
    }
}
