package com.wanderfun.applicationlayer.service;

import com.wanderfun.domainlayer.model.places.Place;

import java.util.List;

public interface PlaceService extends BaseService<Place>{
    List<Place> findAllByNameContaining(String name);
    Place findByName(String name);
    Place findByLongitudeAndLatitude(double longitude, double latitude);
    List<Place> findByOrderByCheckInCountDesc();
}
