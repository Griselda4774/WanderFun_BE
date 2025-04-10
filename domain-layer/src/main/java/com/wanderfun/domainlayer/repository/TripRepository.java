package com.wanderfun.domainlayer.repository;

import com.wanderfun.domainlayer.model.trips.Trip;

import java.util.List;
import java.util.Optional;

public interface TripRepository extends BaseRepository<Trip, Long> {
    Optional<Trip> findByName(String name);
    List<Trip> findAllByUser_Id(Long userId);
    void deleteAllByUser_Id(Long userId);
}
