package com.project2.wanderfun.infrastructure.repository;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.repository.TripPlaceRepository;
import com.project2.wanderfun.domain.model.TripPlace;
import com.project2.wanderfun.infrastructure.persistence.entity.TripPlaceEntity;
import com.project2.wanderfun.infrastructure.persistence.jpaRepository.JpaTripPlaceRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TripPlaceRepositoryImpl extends BaseRepositoryImpl<TripPlace, TripPlaceEntity, Long> implements TripPlaceRepository {

    public TripPlaceRepositoryImpl(JpaTripPlaceRepository jpaTripPlaceRepository, ObjectMapper objectMapper) {
        super(jpaTripPlaceRepository, objectMapper, TripPlace.class, TripPlaceEntity.class);
    }

}
