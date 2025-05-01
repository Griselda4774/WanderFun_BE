package com.wanderfun.domainlayer.model.trips;

import java.util.Date;
import java.util.List;

public class Trip {
    private Long id;
    private String name;
    private Date startTime;
    private Date endTime;
    private Long userId;
    private List<TripPlace> listTripPlaces;

    public Trip() {}

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<TripPlace> getListTripPlaces() {
        return listTripPlaces;
    }

    public void setListTripPlaces(List<TripPlace> listTripPlaces) {
        this.listTripPlaces = listTripPlaces;
    }
}
