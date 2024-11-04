package com.project2.wanderfun.domain.model;

import java.util.List;

public class Album {
    private String name;
    private String description;
    private String placeId;
    private List<String> imageUrls;

    public Album() {
    }

    public Album(String name, String description, String placeId, List<String> imageUrls) {
        this.name = name;
        this.description = description;
        this.placeId = placeId;
        this.imageUrls = imageUrls;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPlaceId() {
        return placeId;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
