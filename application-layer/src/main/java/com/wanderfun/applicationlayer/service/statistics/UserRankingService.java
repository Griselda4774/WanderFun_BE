package com.wanderfun.applicationlayer.service.statistics;

import com.wanderfun.domainlayer.model.statistics.UserRanking;

import java.util.List;

public interface UserRankingService {
    List<UserRanking> findTop100();
    List<UserRanking> findTopWithLimit(Long limit);
    UserRanking findByUserId(Long userId);
}
