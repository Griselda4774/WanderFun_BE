package com.project2.wanderfun.infrastructure.service;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.repository.LeaderboardUserRepository;
import com.project2.wanderfun.application.service.LeaderboardUserService;
import com.project2.wanderfun.domain.model.LeaderboardUser;
import org.springframework.stereotype.Service;

@Service
public class LeaderboardUserServiceImpl extends BaseServiceImpl<LeaderboardUser> implements LeaderboardUserService {

    public LeaderboardUserServiceImpl(LeaderboardUserRepository LeaderboardUserRepository, ObjectMapper objectMapper) {
        super(LeaderboardUserRepository, objectMapper, LeaderboardUser.class);
    }
}
