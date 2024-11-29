package com.project2.wanderfun.infrastructure.repository;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.repository.PlaceRepository;
import com.project2.wanderfun.domain.model.Place;
import com.project2.wanderfun.infrastructure.persistence.entity.PlaceEntity;
import com.project2.wanderfun.infrastructure.persistence.jpaRepository.JpaPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlaceRepositoryImpl extends BaseRepositoryImpl<Place, PlaceEntity, Long> implements PlaceRepository {
    private final JpaPlaceRepository jpaPlaceRepository;

    @Autowired
    public PlaceRepositoryImpl(JpaPlaceRepository jpaPlaceRepository, ObjectMapper objectMapper) {
        super(jpaPlaceRepository, objectMapper, Place.class, PlaceEntity.class);
        this.jpaPlaceRepository = jpaPlaceRepository;
    }

    @Override
    public List<Place> findByNameContaining(String name) {
        return jpaPlaceRepository.findByNameContaining(name)
                .stream()
                .map(entity -> objectMapper.map(entity, Place.class))
                .toList();
    }
}
