package com.wanderfun.applicationlayer.service.statistics;

import com.wanderfun.domainlayer.model.statistics.PlaceRanking;

import java.util.List;

public interface PlaceRankingService {
    List<PlaceRanking> findTop100();
    PlaceRanking findByPlaceId(Long placeId);
}
