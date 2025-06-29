package com.wanderfun.applicationlayer.service.statistics;

import com.wanderfun.domainlayer.model.statistics.PlaceRanking;

import java.util.List;

public interface PlaceRankingService {
    List<PlaceRanking> findTop100();
    List<PlaceRanking> findTopWithLimit(Long limit);
    PlaceRanking findByPlaceId(Long placeId);
}
