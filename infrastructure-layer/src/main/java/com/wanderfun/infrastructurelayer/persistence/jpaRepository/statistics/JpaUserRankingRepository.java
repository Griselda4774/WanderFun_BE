package com.wanderfun.infrastructurelayer.persistence.jpaRepository.statistics;

import com.wanderfun.infrastructurelayer.persistence.entity.statistics.UserRankingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaUserRankingRepository extends JpaRepository<UserRankingEntity, Long> {
    @Query("SELECT u FROM UserRankingEntity u ORDER BY u.ranking DESC LIMIT 100")
    List<UserRankingEntity> findTop100();

    @Query("SELECT u FROM UserRankingEntity u WHERE u.userId = :user_id")
    Optional<UserRankingEntity> findByUserId(@Param("user_id")Long userId);
}
