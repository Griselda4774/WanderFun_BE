package com.project2.wanderfun.domain.model.places;

import com.project2.wanderfun.domain.model.addresses.Address;
import com.project2.wanderfun.domain.model.images.Image;

public class Place {
    private Long id;
    private double longitude;
    private double latitude;
    private Address address;
    private String name;
    private PlaceCategory category;
    private Image coverImage;

    public Place() {
    }
}
