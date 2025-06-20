package com.wanderfun.applicationlayer.service.rankings;

import com.wanderfun.domainlayer.model.rankings.UserRanking;

import java.util.List;

public interface UserRankingService {
    List<UserRanking> findTop100();
    UserRanking findByUserId(Long userId);
}
