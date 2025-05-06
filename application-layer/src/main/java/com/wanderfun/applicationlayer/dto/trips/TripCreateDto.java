package com.wanderfun.applicationlayer.dto.trips;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class TripCreateDto {
    private String name;
    private LocalDate startTime;
    private LocalDate endTime;
    private List<TripPlaceCreateDto> tripPlaceList;

    public TripCreateDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public List<TripPlaceCreateDto> getTripPlaceList() {
        return tripPlaceList;
    }

    public void setTripPlaceList(List<TripPlaceCreateDto> tripPlaceList) {
        this.tripPlaceList = tripPlaceList;
    }
}
