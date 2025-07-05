package com.wanderfun.applicationlayer.service.place;

import com.wanderfun.applicationlayer.service.BaseService;
import com.wanderfun.domainlayer.model.places.Place;

import java.util.List;

public interface PlaceService extends BaseService<Place, Long> {
    List<Place> findAllByNameContaining(String name);
    Place findByName(String name);
    Place findByLongitudeAndLatitude(double longitude, double latitude);
    List<Place> findAllByProvinceName(String provinceName);
    List<Place> findAllByCategoryId(Long categoryId);
    List<Place> findAllInBoundingBox(double minLongitude, double minLatitude, double maxLongitude, double maxLatitude);
}
