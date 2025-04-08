package com.project2.wanderfun.infrastructure.service;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.repository.LeaderboardPlaceRepository;
import com.project2.wanderfun.application.service.LeaderboardPlaceService;
import com.project2.wanderfun.domain.model.statistics.LeaderboardPlace;
import org.springframework.stereotype.Service;

@Service
public class LeaderboardPlaceServiceImpl extends BaseServiceImpl<LeaderboardPlace> implements LeaderboardPlaceService {

    public LeaderboardPlaceServiceImpl(LeaderboardPlaceRepository LeaderboardPlaceRepository, ObjectMapper objectMapper) {
        super(LeaderboardPlaceRepository, objectMapper, LeaderboardPlace.class);
    }
}
