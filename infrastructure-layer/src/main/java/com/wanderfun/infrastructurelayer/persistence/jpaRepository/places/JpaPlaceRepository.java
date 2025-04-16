package com.wanderfun.infrastructurelayer.persistence.jpaRepository.places;

import com.wanderfun.infrastructurelayer.persistence.entity.places.PlaceEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaPlaceRepository extends JpaBaseRepository<PlaceEntity, Long> {
    @Query("""
    SELECT p FROM PlaceEntity p
    JOIN p.address a
    JOIN a.province pr
    WHERE pr.name = :provinceName
    """)
    List<PlaceEntity> findByProvinceName(@Param("provinceName") String provinceName);
}