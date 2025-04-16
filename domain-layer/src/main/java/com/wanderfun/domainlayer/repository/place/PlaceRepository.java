package com.wanderfun.domainlayer.repository;

import com.wanderfun.domainlayer.model.places.Place;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends BaseRepository<Place, Long>{
    List<Place> findAllByNameContaining(String name);
    Optional<Place> findByName(String name);
    Optional<Place> findByLongitudeAndLatitude(double longitude, double latitude);
    List<Place> findByOrderByCheckInCountDesc();
}
