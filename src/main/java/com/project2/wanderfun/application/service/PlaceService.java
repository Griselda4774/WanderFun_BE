package com.project2.wanderfun.application.service;

import com.project2.wanderfun.domain.model.Place;

import java.math.BigDecimal;
import java.util.List;

public interface PlaceService extends BaseService<Place>{
    List<Place> findAllByNameContaining(String name);
    Place findByName(String name);
    Place findByLongitudeAndLatitude(double longitude, double latitude);
    List<Place> findByOrderByCheckInCountDesc();
}
