package com.wanderfun.applicationlayer.dto.places;

import com.wanderfun.applicationlayer.dto.PlaceCategoryDto;
import com.wanderfun.applicationlayer.dto.addresses.AddressDto;
import com.wanderfun.applicationlayer.dto.images.ImageDto;

public class PlaceDto {
    private Long id;
    private double longitude;
    private double latitude;
    private AddressDto address;
    private String name;
    private PlaceCategoryDto category;
    private ImageDto coverImage;
    private float rating;
    private int totalRating;
    private int totalFeedback;

    public PlaceDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlaceCategoryDto getCategory() {
        return category;
    }

    public void setCategory(PlaceCategoryDto category) {
        this.category = category;
    }

    public ImageDto getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(ImageDto coverImage) {
        this.coverImage = coverImage;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(int totalRating) {
        this.totalRating = totalRating;
    }

    public int getTotalFeedback() {
        return totalFeedback;
    }

    public void setTotalFeedback(int totalFeedback) {
        this.totalFeedback = totalFeedback;
    }
}
