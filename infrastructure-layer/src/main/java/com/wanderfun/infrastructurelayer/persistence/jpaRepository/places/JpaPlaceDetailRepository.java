package com.wanderfun.infrastructurelayer.persistence.jpaRepository.places;

import com.wanderfun.infrastructurelayer.persistence.entity.places.PlaceDetailEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaBaseRepository;

import java.util.Optional;

public interface JpaPlaceDetailRepository extends JpaBaseRepository<PlaceDetailEntity, Long> {
    Optional<PlaceDetailEntity> findByPlace_Id(Long id);
}
