package com.wanderfun.infrastructurelayer.repository.statistics;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.model.statistics.UserRanking;
import com.wanderfun.domainlayer.repository.statistics.UserRankingRepository;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.statistics.JpaUserRankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRankingRepositoryImpl implements UserRankingRepository {
    private final JpaUserRankingRepository jpaUserRankingRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserRankingRepositoryImpl(JpaUserRankingRepository jpaUserRankingRepository, ObjectMapper objectMapper) {
        this.jpaUserRankingRepository = jpaUserRankingRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<UserRanking> findTop100() {
        return objectMapper.mapList(jpaUserRankingRepository.findTop100(), UserRanking.class);
    }

    @Override
    public List<UserRanking> findTopWithLimit(Long limit) {
        return objectMapper.mapList(jpaUserRankingRepository.findTopWithLimit(PageRequest.of(0, limit.intValue())), UserRanking.class);
    }

    @Override
    public Optional<UserRanking> findByUserId(Long userId) {
        return jpaUserRankingRepository.findByUserId(userId)
                .map(entity -> objectMapper.map(entity, UserRanking.class));
    }
}
