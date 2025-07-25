package com.wanderfun.applicationlayer.dto.trips;

import com.wanderfun.applicationlayer.dto.places.MiniPlaceDto;
import com.wanderfun.applicationlayer.dto.places.PlaceDto;
import com.wanderfun.domainlayer.model.places.Place;

import java.time.LocalDate;
import java.util.Date;

public class TripPlaceDto {
    private Long id;
    private PlaceDto place;
    private Long tripId;
    private LocalDate startTime;
    private LocalDate endTime;
    private String placeNotes;

    public TripPlaceDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlaceDto getPlace() {
        return place;
    }

    public void setPlace(PlaceDto place) {
        this.place = place;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
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

    public String getPlaceNotes() {
        return placeNotes;
    }

    public void setPlaceNotes(String placeNotes) {
        this.placeNotes = placeNotes;
    }
}
