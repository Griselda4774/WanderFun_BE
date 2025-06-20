package com.wanderfun.infrastructurelayer.repository.rankings;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.model.rankings.PlaceRanking;
import com.wanderfun.domainlayer.repository.rankings.PlaceRankingRepository;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.rankings.JpaPlaceRankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PlaceRankingRepositoryImpl implements PlaceRankingRepository {
    private final JpaPlaceRankingRepository jpaPlaceRankingRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public PlaceRankingRepositoryImpl(JpaPlaceRankingRepository jpaPlaceRankingRepository, ObjectMapper objectMapper) {
        this.jpaPlaceRankingRepository = jpaPlaceRankingRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<PlaceRanking> findTop100() {
        return objectMapper.mapList(jpaPlaceRankingRepository.findTop100(), PlaceRanking.class);
    }

    @Override
    public Optional<PlaceRanking> findByPlaceId(Long placeId) {
        return jpaPlaceRankingRepository.findByPlaceId(placeId)
                .map(entity -> objectMapper.map(entity, PlaceRanking.class));
    }
}
