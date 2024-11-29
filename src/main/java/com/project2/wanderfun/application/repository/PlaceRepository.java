package com.project2.wanderfun.application.repository;

import com.project2.wanderfun.domain.model.Place;

import java.util.List;

public interface PlaceRepository extends BaseRepository<Place, Long>{
    List<Place> findByNameContaining(String name);
}
