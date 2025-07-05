package com.wanderfun.domainlayer.repository.place;

import com.wanderfun.domainlayer.model.places.Place;
import com.wanderfun.domainlayer.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends BaseRepository<Place, Long> {
    List<Place> findAllByNameContaining(String name);
    Optional<Place> findByName(String name);
    Optional<Place> findByLongitudeAndLatitude(double longitude, double latitude);
    List<Place> findAllByProvinceName(String provinceName);
    List<Place> findAllByCategoryId(Long categoryId);
    List<Place> findAllInBoundingBox(double minLongitude, double minLatitude, double maxLongitude, double maxLatitude);
}
