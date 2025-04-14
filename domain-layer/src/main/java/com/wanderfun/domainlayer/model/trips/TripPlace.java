package com.wanderfun.domainlayer.model.trips;

import com.wanderfun.domainlayer.model.places.Place;

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
