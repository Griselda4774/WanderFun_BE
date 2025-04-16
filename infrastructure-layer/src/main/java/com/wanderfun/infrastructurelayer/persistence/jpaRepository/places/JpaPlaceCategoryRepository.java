package com.wanderfun.infrastructurelayer.persistence.jpaRepository.places;

import com.wanderfun.infrastructurelayer.persistence.entity.places.PlaceCategoryEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaPlaceCategoryRepository extends JpaBaseRepository<PlaceCategoryEntity, Integer> {
    Optional<PlaceCategoryEntity> findByName(String name);
    Optional<PlaceCategoryEntity> findByNameEn(String nameEn);
}
