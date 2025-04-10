package com.wanderfun.applicationlayer.service;

import com.wanderfun.domainlayer.model.trips.Trip;

import java.util.List;

public interface TripService extends BaseService<Trip> {
   Trip findByName(String name);
    List<Trip> findAllByUserId(Long userId);
    void deleteAllByUserId(Long userId);
}
