package com.wanderfun.infrastructurelayer.persistence.jpaRepository.rankings;

import com.wanderfun.infrastructurelayer.persistence.entity.rankings.PlaceRankingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaPlaceRankingRepository extends JpaRepository<PlaceRankingEntity, Long> {
    @Query("SELECT p FROM PlaceRankingEntity p ORDER BY p.ranking DESC LIMIT 100")
    List<PlaceRankingEntity> findTop100();

    @Query("SELECT p FROM PlaceRankingEntity p WHERE p.placeId = :place_id")
    Optional<PlaceRankingEntity> findByPlaceId(@Param("place_id")Long placeId);
}
