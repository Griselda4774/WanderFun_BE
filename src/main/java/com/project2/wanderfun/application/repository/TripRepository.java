package com.project2.wanderfun.application.repository;

import com.project2.wanderfun.domain.model.Place;
import com.project2.wanderfun.domain.model.Trip;

import java.util.Optional;

public interface TripRepository extends BaseRepository<Trip, Long> {
    Optional<Trip> findByName(String name);
}
