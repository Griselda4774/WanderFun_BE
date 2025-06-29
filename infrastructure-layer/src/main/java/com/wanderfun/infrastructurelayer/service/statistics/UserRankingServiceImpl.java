package com.wanderfun.infrastructurelayer.service.statistics;

import com.wanderfun.applicationlayer.service.statistics.UserRankingService;
import com.wanderfun.domainlayer.model.statistics.UserRanking;
import com.wanderfun.domainlayer.repository.statistics.UserRankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRankingServiceImpl implements UserRankingService {
    private final UserRankingRepository userRankingRepository;

    @Autowired
    public UserRankingServiceImpl(UserRankingRepository userRankingRepository) {
        this.userRankingRepository = userRankingRepository;
    }

    @Override
    public List<UserRanking> findTop100() {
        return userRankingRepository.findTop100();
    }

    @Override
    public List<UserRanking> findTopWithLimit(Long limit) {
        return userRankingRepository.findTopWithLimit(limit);
    }

    @Override
    public UserRanking findByUserId(Long userId) {
        return userRankingRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException(String.format("%s not found for user ID: %d", UserRanking.class.getSimpleName(), userId)));
    }
}
