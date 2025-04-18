package com.wanderfun.infrastructurelayer.repository.places;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.model.places.PlaceDetail;
import com.wanderfun.domainlayer.repository.place.PlaceDetailRepository;
import com.wanderfun.infrastructurelayer.persistence.entity.places.PlaceDetailEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.places.JpaPlaceDetailRepository;
import com.wanderfun.infrastructurelayer.repository.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlaceDetailRepositoryImpl extends BaseRepositoryImpl<PlaceDetail, PlaceDetailEntity, Long> implements PlaceDetailRepository {
    private final JpaPlaceDetailRepository jpaPlaceDetailRepository;

    @Autowired
    public PlaceDetailRepositoryImpl(JpaPlaceDetailRepository jpaPlaceDetailRepository, ObjectMapper objectMapper) {
        super(jpaPlaceDetailRepository, objectMapper, PlaceDetail.class, PlaceDetailEntity.class);
        this.jpaPlaceDetailRepository = jpaPlaceDetailRepository;
    }
}
