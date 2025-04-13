package com.wanderfun.infrastructurelayer.persistence.entity.trips;

import com.wanderfun.infrastructurelayer.persistence.entity.places.Place;

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
