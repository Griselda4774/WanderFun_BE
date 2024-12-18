package com.project2.wanderfun.application.dto.trip;

import com.project2.wanderfun.application.dto.tripplace.TripPlaceDto;

import java.util.Date;
import java.util.List;

public class TripDto {
    private Long id;
    private String name;
    private String imageUrl;
    private Date startTime;
    private Date endTime;
    private List<TripPlaceDto> tripPlaces;

    public TripDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public List<TripPlaceDto> getTripPlaces() {
        return tripPlaces;
    }

    public void setTripPlaces(List<TripPlaceDto> tripPlaces) {
        this.tripPlaces = tripPlaces;
    }
}
