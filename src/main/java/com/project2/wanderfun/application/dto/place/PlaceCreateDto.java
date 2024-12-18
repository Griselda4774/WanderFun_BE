package com.project2.wanderfun.application.dto.place;

import com.project2.wanderfun.application.dto.placeimage.PlaceImageCreateDto;
import com.project2.wanderfun.application.dto.section.SectionCreateDto;
import com.project2.wanderfun.domain.model.enums.PlaceCategory;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

public class PlaceCreateDto {
    @NotBlank(message = "Longitude is required")
    private BigDecimal longitude;
    @NotBlank(message = "Latitude is required")
    private BigDecimal latitude;
    @NotBlank(message = "Address is required")
    private String address;
    @NotBlank(message = "Name is required")
    private String name;
    private String iconUrl;
    private String coverImageUrl;
    private List<SectionCreateDto> description;
    private int checkInPoint;
    private float checkInRange;
    private PlaceCategory category;
    private LocalTime timeOpen;
    private LocalTime timeClose;
    private String alternativeName;
    private String operator;
    private String link;
    private List<PlaceImageCreateDto> placeImages;

    public PlaceCreateDto() {
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public List<SectionCreateDto> getDescription() {
        return description;
    }

    public void setDescription(List<SectionCreateDto> description) {
        this.description = description;
    }

    public int getCheckInPoint() {
        return checkInPoint;
    }

    public void setCheckInPoint(int checkInPoint) {
        this.checkInPoint = checkInPoint;
    }

    public float getCheckInRange() {
        return checkInRange;
    }

    public void setCheckInRange(float checkInRange) {
        this.checkInRange = checkInRange;
    }

    public PlaceCategory getCategory() {
        return category;
    }

    public void setCategory(PlaceCategory category) {
        this.category = category;
    }

    public LocalTime getTimeOpen() {
        return timeOpen;
    }

    public void setTimeOpen(LocalTime timeOpen) {
        this.timeOpen = timeOpen;
    }

    public LocalTime getTimeClose() {
        return timeClose;
    }

    public void setTimeClose(LocalTime timeClose) {
        this.timeClose = timeClose;
    }

    public String getAlternativeName() {
        return alternativeName;
    }

    public void setAlternativeName(String alternativeName) {
        this.alternativeName = alternativeName;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<PlaceImageCreateDto> getPlaceImages() {
        return placeImages;
    }

    public void setPlaceImages(List<PlaceImageCreateDto> placeImages) {
        this.placeImages = placeImages;
    }
}
