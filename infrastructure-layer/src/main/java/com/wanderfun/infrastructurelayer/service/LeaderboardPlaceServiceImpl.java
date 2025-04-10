package com.wanderfun.infrastructurelayer.service;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.repository.LeaderboardPlaceRepository;
import com.wanderfun.applicationlayer.service.LeaderboardPlaceService;
import com.wanderfun.domainlayer.model.statistics.LeaderboardPlace;
import org.springframework.stereotype.Service;

@Service
public class LeaderboardPlaceServiceImpl extends BaseServiceImpl<LeaderboardPlace> implements LeaderboardPlaceService {

    public LeaderboardPlaceServiceImpl(LeaderboardPlaceRepository LeaderboardPlaceRepository, ObjectMapper objectMapper) {
        super(LeaderboardPlaceRepository, objectMapper, LeaderboardPlace.class);
    }
}
