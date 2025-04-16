package com.wanderfun.infrastructurelayer.repository.places;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.model.places.PlaceCategory;
import com.wanderfun.domainlayer.repository.place.PlaceCategoryRepository;
import com.wanderfun.infrastructurelayer.persistence.entity.places.PlaceCategoryEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.places.JpaPlaceCategoryRepository;
import com.wanderfun.infrastructurelayer.repository.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PlaceCategoryRepositoryImpl extends BaseRepositoryImpl<PlaceCategory, PlaceCategoryEntity, Long> implements PlaceCategoryRepository {
    private final JpaPlaceCategoryRepository jpaPlaceCategoryRepository;

    @Autowired
    public PlaceCategoryRepositoryImpl(JpaPlaceCategoryRepository jpaBaseRepository, ObjectMapper objectMapper) {
        super(jpaBaseRepository, objectMapper, PlaceCategory.class, PlaceCategoryEntity.class);
        this.jpaPlaceCategoryRepository = jpaBaseRepository;
    }

    @Override
    public Optional<PlaceCategory> findByName(String name) {
        return jpaPlaceCategoryRepository.findByName(name)
                .map(entity -> objectMapper.map(entity, PlaceCategory.class));
    }

    @Override
    public Optional<PlaceCategory> findByNameEn(String nameEn) {
        return jpaPlaceCategoryRepository.findByNameEn(nameEn)
                .map(entity -> objectMapper.map(entity, PlaceCategory.class));
    }

    @Override
    public Optional<PlaceCategory> findById(Long id) {
        return jpaPlaceCategoryRepository.findById(id)
                .map(entity -> objectMapper.map(entity, PlaceCategory.class));
    }
}
