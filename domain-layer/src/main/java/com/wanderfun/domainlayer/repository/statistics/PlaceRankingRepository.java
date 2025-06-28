package com.wanderfun.domainlayer.repository.statistics;

import com.wanderfun.domainlayer.model.statistics.PlaceRanking;

import java.util.List;
import java.util.Optional;

public interface PlaceRankingRepository {
    List<PlaceRanking> findTop100();
    List<PlaceRanking> findTopWithLimit(Long limit);
    Optional<PlaceRanking> findByPlaceId(Long placeId);
}
