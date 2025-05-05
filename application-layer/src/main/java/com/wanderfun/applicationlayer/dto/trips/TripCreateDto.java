package com.wanderfun.applicationlayer.dto.trips;

import java.util.Date;
import java.util.List;

public class TripCreateDto {
    private String name;
    private Date startTime;
    private Date endTime;
    private List<TripPlaceCreateDto> tripPlaceList;

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

    public List<TripPlaceCreateDto> getTripPlaceList() {
        return tripPlaceList;
    }

    public void setTripPlaceList(List<TripPlaceCreateDto> tripPlaceList) {
        this.tripPlaceList = tripPlaceList;
    }
}
