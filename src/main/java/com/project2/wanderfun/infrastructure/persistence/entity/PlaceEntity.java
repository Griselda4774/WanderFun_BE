package com.project2.wanderfun.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "place")
public class PlaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(precision = 9, scale = 6)
    private BigDecimal longitude;
    @Column(precision = 8, scale = 6)
    private BigDecimal latitude;
    private String address;
    private String name;
    private String iconUrl;
    private String coverImageUrl;
    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SectionEntity> description = new ArrayList<>();
    private int checkInPoint;
    private float checkInRange;
    private String category;
    private LocalTime timeOpen;
    private LocalTime timeClose;
    private String alternativeName;
    private String operator;
    private String link;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlaceImageEntity> placeImages = new ArrayList<>();

    @OneToMany(mappedBy = "place", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<FeedbackEntity> feedbacks = new ArrayList<>();

    public PlaceEntity() {
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

    public List<SectionEntity> getDescription() {
        return description;
    }

    public void setDescription(List<SectionEntity> description) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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

    public List<PlaceImageEntity> getPlaceImages() {
        return placeImages;
    }

    public void setPlaceImages(List<PlaceImageEntity> placeImages) {
        this.placeImages = placeImages;
    }

    public List<FeedbackEntity> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<FeedbackEntity> feedbacks) {
        this.feedbacks = feedbacks;
    }
}