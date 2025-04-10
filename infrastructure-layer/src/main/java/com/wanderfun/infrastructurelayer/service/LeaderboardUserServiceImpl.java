package com.wanderfun.infrastructurelayer.service;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.repository.LeaderboardUserRepository;
import com.wanderfun.applicationlayer.service.LeaderboardUserService;
import com.wanderfun.domainlayer.model.statistics.LeaderboardUser;
import org.springframework.stereotype.Service;

@Service
public class LeaderboardUserServiceImpl extends BaseServiceImpl<LeaderboardUser> implements LeaderboardUserService {

    public LeaderboardUserServiceImpl(LeaderboardUserRepository LeaderboardUserRepository, ObjectMapper objectMapper) {
        super(LeaderboardUserRepository, objectMapper, LeaderboardUser.class);
    }
}
