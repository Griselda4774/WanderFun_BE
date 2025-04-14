//package com.wanderfun.infrastructurelayer.repository;
//
//import com.wanderfun.applicationlayer.mapper.ObjectMapper;
//import com.wanderfun.domainlayer.repository.LeaderboardUserRepository;
//import com.wanderfun.domainlayer.model.statistics.LeaderboardUser;
//import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaLeaderboardUserRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class LeaderboardUserRepositoryImpl extends BaseRepositoryImpl<LeaderboardUser, LeaderboardUserEntity, Long> implements LeaderboardUserRepository {
//
//    public LeaderboardUserRepositoryImpl(JpaLeaderboardUserRepository jpaLeaderboardUserRepository, ObjectMapper objectMapper) {
//        super(jpaLeaderboardUserRepository, objectMapper, LeaderboardUser.class, LeaderboardUserEntity.class);
//    }
//
//}
