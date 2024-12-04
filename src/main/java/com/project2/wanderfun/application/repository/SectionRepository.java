package com.project2.wanderfun.application.repository;

import com.project2.wanderfun.domain.model.Section;

import java.util.List;

public interface SectionRepository extends BaseRepository<Section, Long>{
    List<Section> findAllByPlaceId(Long placeId);
}
