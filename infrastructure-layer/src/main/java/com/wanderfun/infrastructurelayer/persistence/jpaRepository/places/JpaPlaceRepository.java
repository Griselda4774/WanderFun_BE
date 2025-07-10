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
        WHERE LOWER(p.address.province.nameEn) = LOWER(:province_name)
        OR LOWER(p.address.province.fullNameEn) = LOWER(:province_name)
        OR LOWER(p.address.province.name) = LOWER(:province_name)
        OR LOWER(p.address.province.fullName) = LOWER(:province_name)
    """)
    List<PlaceEntity> findByProvinceName(@Param("province_name") String provinceName);

    @Query("""
       SELECT DISTINCT p FROM PlaceEntity p
       JOIN FETCH p.category c
       WHERE p.name LIKE CONCAT('%', :name, '%')
       OR c.name LIKE CONCAT('%', :name, '%')
       OR c.nameEn LIKE CONCAT('%', :name, '%')
       ORDER BY p.name ASC
    """)
    List<PlaceEntity> findAllByNameContaining(String name);

    @Query("SELECT p FROM PlaceEntity p WHERE p.name = :name")
    Optional<PlaceEntity> findByName(@Param("name")String name);
    Optional<PlaceEntity> findByLongitudeAndLatitude(double longitude, double latitude);

    @Query("""
        SELECT p FROM PlaceEntity p
        WHERE p.category.id = :category_id
    """)
    List<PlaceEntity> findAllByCategoryId(@Param("category_id")Long categoryId);

    @Query("""
        SELECT p FROM PlaceEntity p
        WHERE p.longitude BETWEEN :minLong AND :maxLong
        AND p.latitude BETWEEN :minLat AND :maxLat
    """)
    List<PlaceEntity> findAllInBoundingBox(@Param("minLong") double minLongitude,
                                           @Param("maxLong") double maxLongitude,
                                           @Param("minLat") double minLatitude,
                                           @Param("maxLat") double maxLatitude
    );
}