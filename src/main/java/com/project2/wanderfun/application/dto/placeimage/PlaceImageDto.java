package com.project2.wanderfun.application.dto.placeimage;

public class PlaceImageDto {
    private Long id;
    private String imageUrl;
    private Long placeId;

    public PlaceImageDto() {};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }
}