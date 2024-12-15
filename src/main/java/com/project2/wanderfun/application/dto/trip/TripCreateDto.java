package com.project2.wanderfun.application.dto.trip;

import com.project2.wanderfun.domain.model.TripPlace;

import java.util.Date;
import java.util.List;

public class TripCreateDto {
    private String name;
    private Date startTime;
    private Date endTime;
    private List<TripPlace> tripPlaces;

    public TripCreateDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<TripPlace> getTripPlaces() {
        return tripPlaces;
    }

    public void setTripPlaces(List<TripPlace> tripPlaces) {
        this.tripPlaces = tripPlaces;
    }
}