package com.project2.wanderfun.application.dto.place;

import com.project2.wanderfun.application.dto.feedbackimage.FeedbackImageDto;
import com.project2.wanderfun.application.dto.placeimage.PlaceImageDto;
import com.project2.wanderfun.domain.model.enums.PlaceCategory;
import com.project2.wanderfun.domain.model.Section;
import jakarta.validation.constraints.Digits;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

public class PlaceDto {
    private Long id;
    @Digits(integer = 3, fraction = 6, message = "Longitude must have at most 3 integer digits and 6 fraction digits")
    private BigDecimal longitude;
    @Digits(integer = 2, fraction = 6, message = "Latitude must have at most 2 integer digits and 6 fraction digits")
    private BigDecimal latitude;
    private String address;
    private String name;
    private String iconUrl;
    private String coverImageUrl;
    private List<Section> description;
    private int checkInPoint;
    private float checkInRange;
    private PlaceCategory category;
    private LocalTime timeOpen;
    private LocalTime timeClose;
    private String alternativeName;
    private String operator;
    private String link;
    private List<PlaceImageDto> placeImages;
    private List<FeedbackImageDto> feedbacks;

    public PlaceDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Section> getDescription() {
        return description;
    }

    public void setDescription(List<Section> description) {
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

    public List<PlaceImageDto> getPlaceImages() {
        return placeImages;
    }

    public void setPlaceImages(List<PlaceImageDto> placeImages) {
        this.placeImages = placeImages;
    }

    public List<FeedbackImageDto> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<FeedbackImageDto> feedbacks) {
        this.feedbacks = feedbacks;
    }
}
