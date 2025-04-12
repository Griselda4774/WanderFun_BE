package com.wanderfun.domainlayer.model.places;

import java.time.LocalTime;

public class PlaceDetail {
    private Long id;
    private Long placeId;
    private String description;
    private int checkInPoint;
    private float checkInRangeMeter;
    private LocalTime timeOpen;
    private LocalTime timeClose;
    private boolean isClosed;
    private String bestTimeToVisit;
    private int priceRangeTop;
    private int priceRangeBottom;
    private boolean isVerified;
    private String alternativeName;
    private String operator;
    private String url;

    public PlaceDetail() {
    }
}
