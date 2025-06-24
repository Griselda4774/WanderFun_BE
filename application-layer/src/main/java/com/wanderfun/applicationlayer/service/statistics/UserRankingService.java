package com.wanderfun.applicationlayer.service.statistics;

import com.wanderfun.domainlayer.model.statistics.UserRanking;

import java.util.List;

public interface UserRankingService {
    List<UserRanking> findTop100();
    UserRanking findByUserId(Long userId);
}
