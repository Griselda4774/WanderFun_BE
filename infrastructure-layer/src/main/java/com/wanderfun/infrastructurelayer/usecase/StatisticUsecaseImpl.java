package com.wanderfun.infrastructurelayer.usecase;

import com.wanderfun.applicationlayer.dto.statistics.PlaceRankingDto;
import com.wanderfun.applicationlayer.dto.statistics.UserRankingDto;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.statistics.PlaceRankingService;
import com.wanderfun.applicationlayer.service.statistics.UserRankingService;
import com.wanderfun.applicationlayer.usecase.StatisticUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticUsecaseImpl implements StatisticUsecase {

    PlaceRankingService placeRankingService;
    UserRankingService userRankingService;
    ObjectMapper objectMapper;

    @Autowired
    public StatisticUsecaseImpl(PlaceRankingService placeRankingService, UserRankingService userRankingService, ObjectMapper objectMapper) {
        this.placeRankingService = placeRankingService;
        this.userRankingService = userRankingService;
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
}
