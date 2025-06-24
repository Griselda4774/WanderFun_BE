package com.wanderfun.infrastructurelayer.service.rankings;

import com.wanderfun.applicationlayer.service.statistics.PlaceRankingService;
import com.wanderfun.domainlayer.model.statistics.PlaceRanking;
import com.wanderfun.domainlayer.repository.rankings.PlaceRankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceRankingServiceImpl implements PlaceRankingService {
    private final PlaceRankingRepository placeRankingRepository;

    @Autowired
    public PlaceRankingServiceImpl(PlaceRankingRepository placeRankingRepository) {
        this.placeRankingRepository = placeRankingRepository;
    }

    @Override
    public List<PlaceRanking> findTop100() {
        return placeRankingRepository.findTop100();
    }

    @Override
    public PlaceRanking findByPlaceId(Long placeId) {
        return placeRankingRepository.findByPlaceId(placeId)
                .orElseThrow(() -> new RuntimeException(String.format("%s not found for place ID: %d", PlaceRanking.class.getSimpleName(), placeId)));
    }
}
