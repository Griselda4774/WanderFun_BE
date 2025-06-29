package com.wanderfun.infrastructurelayer.repository.statistics;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.model.statistics.PlaceRanking;
import com.wanderfun.domainlayer.model.statistics.UserRanking;
import com.wanderfun.domainlayer.repository.statistics.PlaceRankingRepository;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.statistics.JpaPlaceRankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
    public List<PlaceRanking> findTopWithLimit(Long limit) {
        return objectMapper.mapList(jpaPlaceRankingRepository.findTopWithLimit(PageRequest.of(0, limit.intValue())), PlaceRanking.class);
    }

    @Override
    public Optional<PlaceRanking> findByPlaceId(Long placeId) {
        return jpaPlaceRankingRepository.findByPlaceId(placeId)
                .map(entity -> objectMapper.map(entity, PlaceRanking.class));
    }
}
