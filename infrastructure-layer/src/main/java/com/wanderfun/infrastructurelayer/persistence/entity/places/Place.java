package com.wanderfun.infrastructurelayer.persistence.entity.places;

import com.wanderfun.infrastructurelayer.persistence.entity.addresses.Address;
import com.wanderfun.infrastructurelayer.persistence.entity.images.Image;

public class Place {
    private Long id;
    private double longitude;
    private double latitude;
    private Address address;
    private String name;
    private PlaceCategory category;
    private Image coverImage;
    private float rating;
    private int totalRating;
    private int totalFeedback;

    public Place() {
    }
}
