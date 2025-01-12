package com.project2.wanderfun.application.service;

import com.project2.wanderfun.domain.model.Trip;

import java.util.List;

public interface TripService extends BaseService<Trip> {
    public Trip findByName(String name);
    public List<Trip> findAllByUserId(Long userId);
    public void deleteAllByUserId(Long userId);
}
