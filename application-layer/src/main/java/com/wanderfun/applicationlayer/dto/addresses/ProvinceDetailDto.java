package com.wanderfun.applicationlayer.dto.addresses;

import com.wanderfun.applicationlayer.dto.images.ImageDto;

public class ProvinceDetailDto {
    private Integer id;
    private String provinceName;
    private double longitude;
    private double latitude;
    private double area_km2;
    private String description;
    private String bestTimeToVisit;
    private String festivals;
    private String localTransport;
    private String localFood;
    private String howToGetThere;
    private ImageDto coverImage;

    public ProvinceDetailDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvinceCode() {
        return provinceName;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceName = provinceCode;
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

    public double getArea_km2() {
        return area_km2;
    }

    public void setArea_km2(double area_km2) {
        this.area_km2 = area_km2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBestTimeToVisit() {
        return bestTimeToVisit;
    }

    public void setBestTimeToVisit(String bestTimeToVisit) {
        this.bestTimeToVisit = bestTimeToVisit;
    }

    public String getFestivals() {
        return festivals;
    }

    public void setFestivals(String festivals) {
        this.festivals = festivals;
    }

    public String getLocalTransport() {
        return localTransport;
    }

    public void setLocalTransport(String localTransport) {
        this.localTransport = localTransport;
    }

    public String getLocalFood() {
        return localFood;
    }

    public void setLocalFood(String localFood) {
        this.localFood = localFood;
    }

    public String getHowToGetThere() {
        return howToGetThere;
    }

    public void setHowToGetThere(String howToGetThere) {
        this.howToGetThere = howToGetThere;
    }

    public ImageDto getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(ImageDto coverImage) {
        this.coverImage = coverImage;
    }
}
