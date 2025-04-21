package com.wanderfun.domainlayer.model.trips;

import com.wanderfun.domainlayer.model.places.Place;

import java.util.Date;

public class TripPlace {
    private Long id;
    private Place place;
    private Long tripId;
    private Date startTime;
    private Date endTime;
    private String placeNote;

    public TripPlace() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPlaceNote() {
        return placeNote;
    }

    public void setPlaceNote(String placeNote) {
        this.placeNote = placeNote;
    }
}
