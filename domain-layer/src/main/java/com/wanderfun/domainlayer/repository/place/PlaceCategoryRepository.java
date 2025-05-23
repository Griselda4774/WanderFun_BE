package com.wanderfun.domainlayer.repository.place;

import com.wanderfun.domainlayer.model.places.PlaceCategory;
import com.wanderfun.domainlayer.repository.BaseRepository;

import java.util.Optional;

public interface PlaceCategoryRepository extends BaseRepository<PlaceCategory, Long> {
    Optional<PlaceCategory> findByName(String name);
    Optional<PlaceCategory> findByNameEn(String nameEn);
}
