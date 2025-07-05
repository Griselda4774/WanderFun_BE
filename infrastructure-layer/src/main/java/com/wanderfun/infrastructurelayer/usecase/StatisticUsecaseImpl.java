package com.wanderfun.infrastructurelayer.usecase;

import com.wanderfun.applicationlayer.dto.statistics.PlaceRankingDto;
import com.wanderfun.applicationlayer.dto.statistics.StatisticDto;
import com.wanderfun.applicationlayer.dto.statistics.UserRankingDto;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.checkins.CheckInService;
import com.wanderfun.applicationlayer.service.place.PlaceService;
import com.wanderfun.applicationlayer.service.statistics.PlaceRankingService;
import com.wanderfun.applicationlayer.service.statistics.UserRankingService;
import com.wanderfun.applicationlayer.service.users.UserService;
import com.wanderfun.applicationlayer.usecase.StatisticUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticUsecaseImpl implements StatisticUsecase {
    private PlaceService placeService;
    private UserService userService;
    private CheckInService checkInService;
    private PlaceRankingService placeRankingService;
    private UserRankingService userRankingService;
    private final ObjectMapper objectMapper;

    @Autowired
    public StatisticUsecaseImpl(PlaceService placeService,
                                UserService userService,
                                PlaceRankingService placeRankingService,
                                UserRankingService userRankingService,
                                CheckInService checkInService,
                                ObjectMapper objectMapper) {
        this.placeRankingService = placeRankingService;
        this.userRankingService = userRankingService;
        this.placeService = placeService;
        this.userService = userService;
        this.checkInService = checkInService;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<PlaceRankingDto> getPlaceRanking() {
        return objectMapper.mapList(placeRankingService.findTop100(), PlaceRankingDto.class);
    }

    @Override
    public List<UserRankingDto> getUserRanking() {
        return objectMapper.mapList(userRankingService.findTop100(), UserRankingDto.class);
    }

    @Override
    public StatisticDto getStatistics() {
        StatisticDto statisticDto = new StatisticDto();
        statisticDto.setTotalPlaces(placeService.count());
        statisticDto.setTotalUsers(userService.count());
        statisticDto.setTotalCheckInAllTime(checkInService.count());
        statisticDto.setTotalCheckInToday(checkInService.countTotalCheckInsToday());
        statisticDto.setTopCheckInPlaces(objectMapper.mapList(placeRankingService.findTopWithLimit(5L), PlaceRankingDto.class));
        statisticDto.setTopCheckInUsers(objectMapper.mapList(userRankingService.findTopWithLimit(5L), UserRankingDto.class));

        return statisticDto;
    }
}
