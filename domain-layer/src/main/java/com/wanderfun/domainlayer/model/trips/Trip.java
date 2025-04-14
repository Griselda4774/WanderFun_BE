package com.wanderfun.domainlayer.model.trips;

import java.util.Date;
import java.util.List;

public class Trip {
    private Long id;
    private String name;
    private Date startTime;
    private Date endTime;
    private Long authorId;
    private List<TripPlace> listTripPlaces;

    public Trip() {
    }
}
