package com.project2.wanderfun.application.service;

import com.project2.wanderfun.domain.model.Trip;

import java.util.List;

public interface TripService extends BaseService<Trip> {
   Trip findByName(String name);
    List<Trip> findAllByUserId(Long userId);
    void deleteAllByUserId(Long userId);
}
