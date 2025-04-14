package com.wanderfun.domainlayer.repository;

import com.wanderfun.domainlayer.model.places.Section;

import java.util.List;

public interface SectionRepository extends BaseRepository<Section, Long>{
    List<Section> findAllByPlaceId(Long placeId);
}
