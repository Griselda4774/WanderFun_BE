package com.project2.wanderfun.application.dto.trip;

import com.project2.wanderfun.domain.model.TripPlace;

import java.util.Date;
import java.util.List;

public class TripCreateDto {
    private String name;
    private String imageUrl;
    private Date startTime;
    private Date endTime;
    private List<TripPlace> tripPlaces;
    private Long userId;

    public TripCreateDto() {
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

    public List<TripPlace> getTripPlaces() {
        return tripPlaces;
    }

    public void setTripPlaces(List<TripPlace> tripPlaces) {
        this.tripPlaces = tripPlaces;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
