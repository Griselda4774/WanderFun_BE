package com.wanderfun.domainlayer.repository.trip;

import com.wanderfun.domainlayer.model.trips.Trip;
import com.wanderfun.domainlayer.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface TripRepository extends BaseRepository<Trip, Long> {
    Optional<Trip> findByName(String name);
    List<Trip> findAllByUser_Id(Long userId);
    void deleteAllByUserId(Long userId);
}
