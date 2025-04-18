package com.wanderfun.infrastructurelayer.persistence.jpaRepository.places;

import com.wanderfun.infrastructurelayer.persistence.entity.places.PlaceEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaPlaceRepository extends JpaBaseRepository<PlaceEntity, Long> {
    @Query("""
        SELECT p FROM PlaceEntity p
        WHERE p.address.province.name = :provinceName
    """)
    List<PlaceEntity> findByProvinceName(@Param("provinceName") String provinceName);

    List<PlaceEntity> findAllByNameContaining(String name);
    @Query("SELECT p FROM PlaceEntity p WHERE p.name = :name")
    Optional<PlaceEntity> findByName(@Param("name")String name);
    Optional<PlaceEntity> findByLongitudeAndLatitude(double longitude, double latitude);
}