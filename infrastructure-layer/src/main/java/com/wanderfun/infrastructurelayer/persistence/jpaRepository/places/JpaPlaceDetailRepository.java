package com.wanderfun.infrastructurelayer.persistence.jpaRepository.places;

import com.wanderfun.infrastructurelayer.persistence.entity.places.PlaceDetailEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaPlaceDetailRepository extends JpaBaseRepository<PlaceDetailEntity, Long> {
    @Query("SELECT pd FROM PlaceDetailEntity pd WHERE pd.place.id = :id")
    Optional<PlaceDetailEntity> findByPlaceId(@Param("id")Long id);
}
