package com.wanderfun.infrastructurelayer.repository.trips;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.repository.trip.TripPlaceRepository;
import com.wanderfun.domainlayer.model.trips.TripPlace;
import com.wanderfun.infrastructurelayer.persistence.entity.trips.TripPlaceEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.trips.JpaTripPlaceRepository;
import com.wanderfun.infrastructurelayer.repository.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TripPlaceRepositoryImpl extends BaseRepositoryImpl<TripPlace, TripPlaceEntity, Long> implements TripPlaceRepository {

    @Autowired
    public TripPlaceRepositoryImpl(JpaTripPlaceRepository jpaTripPlaceRepository, ObjectMapper objectMapper) {
        super(jpaTripPlaceRepository, objectMapper, TripPlace.class, TripPlaceEntity.class);
    }

}
