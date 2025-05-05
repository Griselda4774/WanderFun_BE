package com.wanderfun.applicationlayer.dto.trips;

import com.wanderfun.domainlayer.model.places.Place;

import java.util.Date;

public class TripPlaceCreateDto {

    private Long placeId;
    private Date startTime;
    private Date endTime;
    private String placeNote;

    public TripPlaceCreateDto() {
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
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
