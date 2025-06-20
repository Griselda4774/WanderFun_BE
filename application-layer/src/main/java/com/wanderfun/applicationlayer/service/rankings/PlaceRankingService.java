package com.wanderfun.applicationlayer.service.rankings;

import com.wanderfun.domainlayer.model.rankings.PlaceRanking;

import java.util.List;

public interface PlaceRankingService {
    List<PlaceRanking> findTop100();
    PlaceRanking findByPlaceId(Long placeId);
}
