package com.project2.wanderfun.infrastructure.repository;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.repository.PlaceRepository;
import com.project2.wanderfun.application.repository.SectionRepository;
import com.project2.wanderfun.domain.model.Place;
import com.project2.wanderfun.infrastructure.persistence.entity.PlaceEntity;
import com.project2.wanderfun.infrastructure.persistence.entity.SectionEntity;
import com.project2.wanderfun.infrastructure.persistence.jpaRepository.JpaPlaceRepository;
import com.project2.wanderfun.infrastructure.persistence.jpaRepository.JpaSectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PlaceRepositoryImpl extends BaseRepositoryImpl<Place, PlaceEntity, Long> implements PlaceRepository {
    private final JpaPlaceRepository jpaPlaceRepository;
    private final JpaSectionRepository jpaSectionRepository;

    @Autowired
    public PlaceRepositoryImpl(JpaPlaceRepository jpaPlaceRepository, ObjectMapper objectMapper, JpaSectionRepository jpaSectionRepository) {
        super(jpaPlaceRepository, objectMapper, Place.class, PlaceEntity.class);
        this.jpaPlaceRepository = jpaPlaceRepository;
        this.jpaSectionRepository = jpaSectionRepository;
    }

    @Override
    public Place save(Place place) {
        PlaceEntity placeEntity = objectMapper.map(place, PlaceEntity.class);

        List<SectionEntity> sectionEntities = objectMapper.mapList(place.getDescription(), SectionEntity.class);

        placeEntity.setDescription(null);
        PlaceEntity savedPlaceEntity = jpaPlaceRepository.save(placeEntity);

        if(sectionEntities != null) {
            sectionEntities.forEach(sectionEntity -> sectionEntity.setPlace(savedPlaceEntity));
        }

        savedPlaceEntity.setDescription(sectionEntities);
        PlaceEntity newSavedPlaceEntity = jpaPlaceRepository.save(savedPlaceEntity);
        return objectMapper.map(newSavedPlaceEntity, Place.class);
    }

    @Override
    public List<Place> findAllByNameContaining(String name) {
        return jpaPlaceRepository.findAllByNameContaining(name)
                .stream()
                .map(entity -> objectMapper.map(entity, Place.class))
                .toList();
    }

    @Override
    public Optional<Place> findByName(String name) {
        return jpaPlaceRepository.findByName(name)
                .map(entity -> objectMapper.map(entity, Place.class));
    }
}
