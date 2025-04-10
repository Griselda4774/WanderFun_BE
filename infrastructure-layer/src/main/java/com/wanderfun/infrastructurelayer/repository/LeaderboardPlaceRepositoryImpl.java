package com.wanderfun.infrastructurelayer.repository;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.repository.LeaderboardPlaceRepository;
import com.wanderfun.domainlayer.model.statistics.LeaderboardPlace;
import com.wanderfun.infrastructurelayer.persistence.entity.LeaderboardPlaceEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaLeaderboardPlaceRepository;
import org.springframework.stereotype.Repository;

@Repository
public class LeaderboardPlaceRepositoryImpl extends BaseRepositoryImpl<LeaderboardPlace, LeaderboardPlaceEntity, Long> implements LeaderboardPlaceRepository {

    public LeaderboardPlaceRepositoryImpl(JpaLeaderboardPlaceRepository jpaLeaderboardPlaceRepository, ObjectMapper objectMapper) {
        super(jpaLeaderboardPlaceRepository, objectMapper, LeaderboardPlace.class, LeaderboardPlaceEntity.class);
    }

}
