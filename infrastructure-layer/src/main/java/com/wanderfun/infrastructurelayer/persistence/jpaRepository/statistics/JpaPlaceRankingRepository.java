package com.wanderfun.infrastructurelayer.persistence.jpaRepository.statistics;

import com.wanderfun.infrastructurelayer.persistence.entity.statistics.PlaceRankingEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.statistics.UserRankingEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaPlaceRankingRepository extends JpaRepository<PlaceRankingEntity, Long> {
    @Query("SELECT p FROM PlaceRankingEntity p ORDER BY p.ranking ASC LIMIT 100")
    List<PlaceRankingEntity> findTop100();

    @Query("SELECT p FROM PlaceRankingEntity p ORDER BY p.ranking ASC")
    List<PlaceRankingEntity> findTopWithLimit(Pageable pageable);

    @Query("SELECT p FROM PlaceRankingEntity p WHERE p.placeId = :place_id")
    Optional<PlaceRankingEntity> findByPlaceId(@Param("place_id")Long placeId);
}
