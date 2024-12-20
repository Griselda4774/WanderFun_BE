package com.project2.wanderfun.infrastructure.repository;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.repository.PlaceRepository;
import com.project2.wanderfun.application.repository.SectionRepository;
import com.project2.wanderfun.domain.model.Place;
import com.project2.wanderfun.domain.model.Section;
import com.project2.wanderfun.infrastructure.persistence.entity.PlaceEntity;
import com.project2.wanderfun.infrastructure.persistence.entity.PlaceImageEntity;
import com.project2.wanderfun.infrastructure.persistence.entity.SectionEntity;
import com.project2.wanderfun.infrastructure.persistence.jpaRepository.JpaPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class PlaceRepositoryImpl extends BaseRepositoryImpl<Place, PlaceEntity, Long> implements PlaceRepository {
    private final JpaPlaceRepository jpaPlaceRepository;

    @Autowired
    public PlaceRepositoryImpl(JpaPlaceRepository jpaPlaceRepository, ObjectMapper objectMapper) {
        super(jpaPlaceRepository, objectMapper, Place.class, PlaceEntity.class);
        this.jpaPlaceRepository = jpaPlaceRepository;
    }

    @Override
    public Place save(Place place) {
        PlaceEntity placeEntity = objectMapper.map(place, PlaceEntity.class);

        if (place.getDescription() != null || place.getDescription().size() > 0) {
            List<SectionEntity> sectionEntities = objectMapper.mapList(place.getDescription(), SectionEntity.class);
            sectionEntities.forEach(sectionEntity -> sectionEntity.setPlace(placeEntity));
            placeEntity.setDescription(sectionEntities);
        }

        if (place.getPlaceImages() != null || place.getPlaceImages().size() > 0) {
            List<PlaceImageEntity> placeImageEntities = objectMapper.mapList(place.getPlaceImages(), PlaceImageEntity.class);
            placeImageEntities.forEach(placeImageEntity -> placeImageEntity.setPlace(placeEntity));
            placeEntity.setPlaceImages(placeImageEntities);
        }

        PlaceEntity savedPlaceEntity = jpaPlaceRepository.save(placeEntity);

        return objectMapper.map(savedPlaceEntity, Place.class);
    }

    @Override
    public List<Place> saveAll(List<Place> places) {
        List<PlaceEntity> placeEntities = places.stream()
                .map(place -> objectMapper.map(place, PlaceEntity.class))
                .toList();

        List<PlaceEntity> savedPlaceEntities = jpaPlaceRepository.saveAll(placeEntities);

        return savedPlaceEntities.stream()
                .map(placeEntity -> objectMapper.map(placeEntity, Place.class))
                .toList();
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
        Optional<Place> place = jpaPlaceRepository.findByName(name)
                .map(entity -> objectMapper.map(entity, Place.class));
        return place;
    }

    @Override
    public Optional<Place> findByLongitudeAndLatitude(BigDecimal longitude, BigDecimal latitude) {
        Optional<Place> place = jpaPlaceRepository.findByLongitudeAndLatitude(longitude, latitude)
                .map(entity -> objectMapper.map(entity, Place.class));
        return place;
    }
}
