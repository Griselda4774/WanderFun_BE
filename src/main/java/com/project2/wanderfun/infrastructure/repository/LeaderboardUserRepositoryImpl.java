package com.project2.wanderfun.infrastructure.repository;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.repository.LeaderboardUserRepository;
import com.project2.wanderfun.domain.model.statistics.LeaderboardUser;
import com.project2.wanderfun.infrastructure.persistence.entity.LeaderboardUserEntity;
import com.project2.wanderfun.infrastructure.persistence.jpaRepository.JpaLeaderboardUserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class LeaderboardUserRepositoryImpl extends BaseRepositoryImpl<LeaderboardUser, LeaderboardUserEntity, Long> implements LeaderboardUserRepository {

    public LeaderboardUserRepositoryImpl(JpaLeaderboardUserRepository jpaLeaderboardUserRepository, ObjectMapper objectMapper) {
        super(jpaLeaderboardUserRepository, objectMapper, LeaderboardUser.class, LeaderboardUserEntity.class);
    }

}
