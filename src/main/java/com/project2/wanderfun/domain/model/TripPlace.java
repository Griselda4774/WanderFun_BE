package com.project2.wanderfun.domain.model;

import java.util.Date;

public class TripPlace {
    private Place place;
    private Date startTime;
    private Date endTime;

    public TripPlace() {
    }

    public TripPlace(Place place, Date startTime, Date endTime) {
        this.place = place;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Place getPlace() {
        return place;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
