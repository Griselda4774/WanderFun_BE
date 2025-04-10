package com.wanderfun.infrastructurelayer.persistence.entity.places;

import java.time.LocalTime;

public class PlaceDetail {
    private Long id;
    private Long placeId;
    private String description;
    private int checkInPoint;
    private float checkInRange;
    private PlaceCategory category;
    private LocalTime timeOpen;
    private LocalTime timeClose;
    private boolean isOpen;
    private String bestTimeToVisit;
    private int priceRangeTop;
    private int priceRangeBottom;
    private boolean isVerified;
    private String alternativeName;
    private String operator;
    private String link;
    private float rating;
    private int totalRating;
    private int totalFeedback;

    public PlaceDetail() {
    }
}
