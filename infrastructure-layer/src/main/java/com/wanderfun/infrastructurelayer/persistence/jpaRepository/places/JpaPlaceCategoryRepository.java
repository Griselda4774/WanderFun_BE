package com.wanderfun.infrastructurelayer.persistence.jpaRepository.places;

import com.wanderfun.infrastructurelayer.persistence.entity.places.PlaceCategoryEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPlaceCategoryRepository extends JpaBaseRepository<PlaceCategoryEntity, Long> {

}
