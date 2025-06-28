package com.wanderfun.applicationlayer.usecase;

import com.wanderfun.applicationlayer.dto.statistics.PlaceRankingDto;
import com.wanderfun.applicationlayer.dto.statistics.StatisticDto;
import com.wanderfun.applicationlayer.dto.statistics.UserRankingDto;

import java.util.List;

public interface StatisticUsecase {
    List<PlaceRankingDto> getPlaceRanking();
    List<UserRankingDto> getUserRanking();
    StatisticDto getStatistics();
}