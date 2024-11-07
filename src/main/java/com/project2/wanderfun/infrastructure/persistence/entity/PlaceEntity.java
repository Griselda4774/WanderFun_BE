package com.project2.wanderfun.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    private Integer checkInPoint;
    private Float checkInRange;
    private String category;
    private LocalDateTime timeOpen;
    private LocalDateTime timeClose;
    private String alternativeName;
    private String operator;
    private String link;

    @OneToMany(mappedBy = "place")
    private List<TripPlaceEntity> tripPlaces;

    @ManyToMany(mappedBy = "favoritePlaces")
    private List<UserEntity> usersWhoFavorited;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<SectionEntity> sections;

    @OneToMany(mappedBy = "place")
    private List<PlaceImageEntity> placeImages;

    @OneToMany(mappedBy = "place")
    private List<FeedbackEntity> feedbacks;

    public PlaceEntity() {
    }

    public Long getId() {
        return id;
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

    public Integer getCheckInPoint() {
        return checkInPoint;
    }

    public Float getCheckInRange() {
        return checkInRange;
    }

    public String getCategory() {
        return category;
    }

    public LocalDateTime getTimeOpen() {
        return timeOpen;
    }

    public LocalDateTime getTimeClose() {
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

    public List<TripPlaceEntity> getTripPlaces() {
        return tripPlaces;
    }

    public List<UserEntity> getUsersWhoFavorited() {
        return usersWhoFavorited;
    }

    public List<SectionEntity> getSections() {
        return sections;
    }

    public List<PlaceImageEntity> getPlaceImages() {
        return placeImages;
    }

    public List<FeedbackEntity> getFeedbacks() {
        return feedbacks;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setCheckInPoint(Integer checkInPoint) {
        this.checkInPoint = checkInPoint;
    }

    public void setCheckInRange(Float checkInRange) {
        this.checkInRange = checkInRange;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTimeOpen(LocalDateTime timeOpen) {
        this.timeOpen = timeOpen;
    }

    public void setTimeClose(LocalDateTime timeClose) {
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

    public void setTripPlaces(List<TripPlaceEntity> tripPlaces) {
        this.tripPlaces = tripPlaces;
    }

    public void setUsersWhoFavorited(List<UserEntity> usersWhoFavorited) {
        this.usersWhoFavorited = usersWhoFavorited;
    }

    public void setSections(List<SectionEntity> sections) {
        this.sections = sections;
    }

    public void setPlaceImages(List<PlaceImageEntity> placeImages) {
        this.placeImages = placeImages;
    }

    public void setFeedbacks(List<FeedbackEntity> feedbacks) {
        this.feedbacks = feedbacks;
    }
}