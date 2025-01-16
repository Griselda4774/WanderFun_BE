package com.project2.wanderfun.application.repository;

import com.project2.wanderfun.domain.model.Place;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends BaseRepository<Place, Long>{
    List<Place> findAllByNameContaining(String name);
    Optional<Place> findByName(String name);
    Optional<Place> findByLongitudeAndLatitude(double longitude, double latitude);
    List<Place> findByOrderByCheckInCountDesc();
}
