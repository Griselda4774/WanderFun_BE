package com.wanderfun.applicationlayer.dto.places;

import com.wanderfun.applicationlayer.dto.addresses.AddressCreateDto;
import com.wanderfun.applicationlayer.dto.images.ImageDto;

public class PlaceCreateDto {
    private double longitude;
    private double latitude;
    private AddressCreateDto address;
    private String name;
    private Integer categoryId;
    private ImageDto coverImage;
    private PlaceDetailDto placeDetail;

    public PlaceCreateDto() {
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

    public AddressCreateDto getAddress() {
        return address;
    }

    public void setAddress(AddressCreateDto address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public ImageDto getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(ImageDto coverImage) {
        this.coverImage = coverImage;
    }

    public PlaceDetailDto getPlaceDetailDto() {
        return placeDetail;
    }

    public void setPlaceDetail(PlaceDetailDto placeDetail) {
        this.placeDetail = placeDetail;
    }
}
