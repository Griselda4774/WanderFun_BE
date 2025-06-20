package com.wanderfun.infrastructurelayer.service.rankings;

import com.wanderfun.applicationlayer.service.rankings.UserRankingService;
import com.wanderfun.domainlayer.model.rankings.UserRanking;
import com.wanderfun.domainlayer.repository.rankings.UserRankingRepository;
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
    public UserRanking findByUserId(Long userId) {
        return userRankingRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException(String.format("%s not found for user ID: %d", UserRanking.class.getSimpleName(), userId)));
    }
}
