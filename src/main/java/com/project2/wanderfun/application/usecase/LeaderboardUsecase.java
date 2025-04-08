package com.project2.wanderfun.application.usecase;

import com.project2.wanderfun.application.dto.leaderboard.LeaderboardPlaceDto;
import com.project2.wanderfun.application.dto.leaderboard.LeaderboardUserDto;
import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.service.LeaderboardPlaceService;
import com.project2.wanderfun.application.service.LeaderboardUserService;
import com.project2.wanderfun.application.service.PlaceService;
import com.project2.wanderfun.application.service.UserService;
import com.project2.wanderfun.domain.model.statistics.LeaderboardPlace;
import com.project2.wanderfun.domain.model.statistics.LeaderboardUser;
import com.project2.wanderfun.domain.model.places.Place;
import com.project2.wanderfun.domain.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderboardUsecase {
    private final UserService userService;
    private final PlaceService placeService;
    private final LeaderboardUserService leaderboardUserService;
    private final LeaderboardPlaceService leaderboardPlaceService;
    private final ObjectMapper objectMapper;

    @Autowired
    public LeaderboardUsecase(UserService userService, PlaceService placeService, ObjectMapper objectMapper,
                              LeaderboardUserService leaderboardUserService, LeaderboardPlaceService leaderboardPlaceService) {
        this.userService = userService;
        this.placeService = placeService;
        this.objectMapper = objectMapper;
        this.leaderboardUserService = leaderboardUserService;
        this.leaderboardPlaceService = leaderboardPlaceService;
    }

    public List<LeaderboardUserDto> getUserLeaderboard() {
        return objectMapper.mapList(leaderboardUserService.findAll(), LeaderboardUserDto.class);
    }

    public List<LeaderboardPlaceDto> getPlaceLeaderboard() {
        return objectMapper.mapList(leaderboardPlaceService.findAll(), LeaderboardPlaceDto.class);
    }

    public boolean updateLeaderboardUser() {
        List<User> users = userService.findByOrderByPointDesc();
        List<LeaderboardUser> leaderboardUsers = leaderboardUserService.findAll();
        int count = users.size() < 50 ? users.size() : 50;
        int leaderboardCount = leaderboardUsers.size();
        for (int i = 0; i < count; i++) {
            LeaderboardUser leaderboardUser;
            if (i < leaderboardCount) {
                leaderboardUser = leaderboardUsers.get(i);
            } else {
                leaderboardUser = new LeaderboardUser();
            }
            objectMapper.copyProperties(users.get(i), leaderboardUser);
            leaderboardUser.setRank(i + 1);
            leaderboardUser.setUserId(users.get(i).getId());
            users.get(i).setRank(leaderboardUser.getRank());
            if (i < leaderboardCount) {
                leaderboardUserService.updateById(leaderboardUser.getId(), leaderboardUser);
            } else {
                leaderboardUserService.create(leaderboardUser);
            }

            userService.updateById(users.get(i).getId(), users.get(i));
        }
        return true;
    }

    public boolean updateLeaderboardPlace() {
        List<Place> places = placeService.findByOrderByCheckInCountDesc();
        List<LeaderboardPlace> leaderboardPlaces = leaderboardPlaceService.findAll();
        int count = places.size() < 50 ? places.size() : 50;
        int leaderboardCount = leaderboardPlaces.size();
        for (int i = 0; i < count; i++) {
            LeaderboardPlace leaderboardPlace;
            if (i < leaderboardCount) {
                leaderboardPlace = leaderboardPlaces.get(i);
            } else {
                leaderboardPlace = new LeaderboardPlace();
            }
            objectMapper.copyProperties(places.get(i), leaderboardPlace);
            leaderboardPlace.setRank(i + 1);
            if (i < leaderboardCount) {
                leaderboardPlaceService.updateById(leaderboardPlace.getId(), leaderboardPlace);
            } else {
                leaderboardPlaceService.create(leaderboardPlace);
            }
        }
        return true;
    }
}
