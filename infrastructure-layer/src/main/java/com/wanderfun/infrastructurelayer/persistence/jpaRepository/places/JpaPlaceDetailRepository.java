package com.wanderfun.infrastructurelayer.persistence.jpaRepository.places;

import com.wanderfun.infrastructurelayer.persistence.entity.places.PlaceDetailEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaPlaceDetailRepository extends JpaBaseRepository<PlaceDetailEntity, Long> {
    Optional<PlaceDetailEntity> findByPlace_Id(Long id);
}
