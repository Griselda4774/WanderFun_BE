package com.wanderfun.domainlayer.model.places;

import com.wanderfun.domainlayer.model.addresses.Address;
import com.wanderfun.domainlayer.model.images.Image;

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
