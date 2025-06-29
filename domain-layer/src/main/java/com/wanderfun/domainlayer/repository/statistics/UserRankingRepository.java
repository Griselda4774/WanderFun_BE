package com.wanderfun.domainlayer.repository.statistics;

import com.wanderfun.domainlayer.model.statistics.UserRanking;

import java.util.List;
import java.util.Optional;

public interface UserRankingRepository {
    List<UserRanking> findTop100();
    List<UserRanking> findTopWithLimit(Long limit);
    Optional<UserRanking> findByUserId(Long userId);
}
