package com.wanderfun.domainlayer.repository.rankings;

import com.wanderfun.domainlayer.model.rankings.UserRanking;

import java.util.List;
import java.util.Optional;

public interface UserRankingRepository {
    List<UserRanking> findTop100();
    Optional<UserRanking> findByUserId(Long userId);
}
