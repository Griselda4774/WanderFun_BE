package com.project2.wanderfun.infrastructure.repository;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.repository.LeaderboardPlaceRepository;
import com.project2.wanderfun.domain.model.LeaderboardPlace;
import com.project2.wanderfun.infrastructure.persistence.entity.LeaderboardPlaceEntity;
import com.project2.wanderfun.infrastructure.persistence.jpaRepository.JpaLeaderboardPlaceRepository;
import org.springframework.stereotype.Repository;

@Repository
public class LeaderboardPlaceRepositoryImpl extends BaseRepositoryImpl<LeaderboardPlace, LeaderboardPlaceEntity, Long> implements LeaderboardPlaceRepository {

    public LeaderboardPlaceRepositoryImpl(JpaLeaderboardPlaceRepository jpaLeaderboardPlaceRepository, ObjectMapper objectMapper) {
        super(jpaLeaderboardPlaceRepository, objectMapper, LeaderboardPlace.class, LeaderboardPlaceEntity.class);
    }

}
