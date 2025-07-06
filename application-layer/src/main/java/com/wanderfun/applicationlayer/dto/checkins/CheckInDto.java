package com.wanderfun.applicationlayer.dto.checkins;

import com.wanderfun.applicationlayer.dto.places.PlaceDto;

import java.time.LocalDateTime;

public class CheckInDto {
    private Long id;
    private Long userId;
    private PlaceDto place;
    private LocalDateTime createdAt;

    public CheckInDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public PlaceDto getPlace() {
        return place;
    }

    public void setPlace(PlaceDto place) {
        this.place = place;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
