package com.wanderfun.domainlayer.repository.rankings;

import com.wanderfun.domainlayer.model.rankings.PlaceRanking;

import java.util.List;
import java.util.Optional;

public interface PlaceRankingRepository {
    List<PlaceRanking> findTop100();
    Optional<PlaceRanking> findByPlaceId(Long placeId);
}
