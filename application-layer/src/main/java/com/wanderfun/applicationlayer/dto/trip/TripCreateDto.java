package com.wanderfun.applicationlayer.dto.trip;

import com.wanderfun.applicationlayer.dto.tripplace.TripPlaceCreateDto;

import java.util.List;

public class TripCreateDto {
    private String name;
    private String imageUrl;
    private String imagePublicId;
    private List<TripPlaceCreateDto> tripPlaces;

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

    public String getImagePublicId() {
        return imagePublicId;
    }

    public void setImagePublicId(String imagePublicId) {
        this.imagePublicId = imagePublicId;
    }

    public List<TripPlaceCreateDto> getTripPlaces() {
        return tripPlaces;
    }

    public void setTripPlaces(List<TripPlaceCreateDto> tripPlaces) {
        this.tripPlaces = tripPlaces;
    }
}
