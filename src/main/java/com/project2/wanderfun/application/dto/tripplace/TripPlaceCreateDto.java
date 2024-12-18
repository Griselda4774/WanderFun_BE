package com.project2.wanderfun.application.dto.tripplace;

import java.math.BigDecimal;
import java.util.Date;

public class TripPlaceCreateDto {
    private BigDecimal placeLongitude;
    private BigDecimal placeLatitude;
    private String placeName;
    private String placeCoverImageUrl;
    private Date startTime;
    private Date endTime;

    public TripPlaceCreateDto() {};

    public BigDecimal getPlaceLongitude() {
        return placeLongitude;
    }

    public void setPlaceLongitude(BigDecimal placeLongitude) {
        this.placeLongitude = placeLongitude;
    }

    public BigDecimal getPlaceLatitude() {
        return placeLatitude;
    }

    public void setPlaceLatitude(BigDecimal placeLatitude) {
        this.placeLatitude = placeLatitude;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceCoverImageUrl() {
        return placeCoverImageUrl;
    }

    public void setPlaceCoverImageUrl(String placeCoverImageUrl) {
        this.placeCoverImageUrl = placeCoverImageUrl;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
