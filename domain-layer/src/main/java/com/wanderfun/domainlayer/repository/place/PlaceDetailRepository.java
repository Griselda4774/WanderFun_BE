package com.wanderfun.domainlayer.repository.place;

import com.wanderfun.domainlayer.model.places.PlaceDetail;
import com.wanderfun.domainlayer.repository.BaseRepository;

import java.util.Optional;

public interface PlaceDetailRepository extends BaseRepository<PlaceDetail, Long> {
    Optional<PlaceDetail> findByPlaceId(Long placeId);
}
