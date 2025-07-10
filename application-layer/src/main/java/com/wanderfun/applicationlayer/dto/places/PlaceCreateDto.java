package com.wanderfun.applicationlayer.dto.places;

import com.wanderfun.applicationlayer.dto.addresses.AddressCreateDto;
import com.wanderfun.applicationlayer.dto.images.ImageDto;
import jakarta.validation.constraints.NotBlank;

public class PlaceCreateDto {
    @NotBlank
    private double longitude;
    @NotBlank
    private double latitude;
    @NotBlank
    private AddressCreateDto address;
    @NotBlank
    private String name;
    @NotBlank
    private Integer categoryId;
    private ImageDto coverImage;
    private int checkInPoint;
    private float checkInRangeMeter;
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

    public int getCheckInPoint() {
        return checkInPoint;
    }

    public void setCheckInPoint(int checkInPoint) {
        this.checkInPoint = checkInPoint;
    }

    public float getCheckInRangeMeter() {
        return checkInRangeMeter;
    }

    public void setCheckInRangeMeter(float checkInRangeMeter) {
        this.checkInRangeMeter = checkInRangeMeter;
    }

    public PlaceDetailDto getPlaceDetail() {
        return placeDetail;
    }
    public void setPlaceDetail(PlaceDetailDto placeDetail) {
        this.placeDetail = placeDetail;
    }
}
