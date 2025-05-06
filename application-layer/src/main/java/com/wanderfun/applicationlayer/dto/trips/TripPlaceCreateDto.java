package com.wanderfun.applicationlayer.dto.trips;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class TripPlaceCreateDto {
    @NotBlank(message = "Place ID cannot be blank")
    private Long placeId;
    @NotBlank(message = "Start time cannot be blank")
    private LocalDate startTime;
    @NotBlank(message = "End time cannot be blank")
    private LocalDate endTime;
    private String placeNotes;

    public TripPlaceCreateDto() {
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
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
