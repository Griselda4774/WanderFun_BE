package com.project2.wanderfun.domain.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Place {
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String address;
    private String name;
    private String iconUrl;
    private String coverImageUrl;
    private List<Section> description;
    private int checkInPoint;
    private float checkInRange;
    private PlaceCategory category;
    private Date timeOpen;
    private Date timeClose;
    private String alternativeName;
    private String operator;
    private String link;
    private List<String> imageUrls;
    private List<Feedback> feedbacks;

    public Place() {
    }

    public Place(BigDecimal longitude, BigDecimal latitude, String address, String name, String iconUrl, String coverImageUrl, List<Section> description, int checkInPoint, float checkInRange, PlaceCategory category, Date timeOpen, Date timeClose, String alternativeName, String operator, String link, List<String> imageUrls, List<Feedback> feedbacks) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.name = name;
        this.iconUrl = iconUrl;
        this.coverImageUrl = coverImageUrl;
        this.description = description;
        this.checkInPoint = checkInPoint;
        this.checkInRange = checkInRange;
        this.category = category;
        this.timeOpen = timeOpen;
        this.timeClose = timeClose;
        this.alternativeName = alternativeName;
        this.operator = operator;
        this.link = link;
        this.imageUrls = imageUrls;
        this.feedbacks = feedbacks;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public List<Section> getDescription() {
        return description;
    }

    public int getCheckInPoint() {
        return checkInPoint;
    }

    public float getCheckInRange() {
        return checkInRange;
    }

    public PlaceCategory getCategory() {
        return category;
    }

    public Date getTimeOpen() {
        return timeOpen;
    }

    public Date getTimeClose() {
        return timeClose;
    }

    public String getAlternativeName() {
        return alternativeName;
    }

    public String getOperator() {
        return operator;
    }

    public String getLink() {
        return link;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public void setDescription(List<Section> description) {
        this.description = description;
    }

    public void setCheckInPoint(int checkInPoint) {
        this.checkInPoint = checkInPoint;
    }

    public void setCheckInRange(float checkInRange) {
        this.checkInRange = checkInRange;
    }

    public void setCategory(PlaceCategory category) {
        this.category = category;
    }

    public void setTimeOpen(Date timeOpen) {
        this.timeOpen = timeOpen;
    }

    public void setTimeClose(Date timeClose) {
        this.timeClose = timeClose;
    }

    public void setAlternativeName(String alternativeName) {
        this.alternativeName = alternativeName;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }
}
