package com.project2.wanderfun.infrastructure.repository;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.repository.TripRepository;
import com.project2.wanderfun.domain.model.Place;
import com.project2.wanderfun.domain.model.Trip;
import com.project2.wanderfun.infrastructure.persistence.entity.*;
import com.project2.wanderfun.infrastructure.persistence.jpaRepository.JpaPlaceRepository;
import com.project2.wanderfun.infrastructure.persistence.jpaRepository.JpaTripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TripRepositoryImpl extends BaseRepositoryImpl<Trip, TripEntity, Long> implements TripRepository {
    private final JpaTripRepository jpaTripRepository;

    @Autowired
    public TripRepositoryImpl(JpaTripRepository jpaTripRepository, ObjectMapper objectMapper) {
        super(jpaTripRepository, objectMapper, Trip.class, TripEntity.class);
        this.jpaTripRepository = jpaTripRepository;
    }

    @Override
    public Trip save(Trip trip) {
        TripEntity tripEntity = objectMapper.map(trip, TripEntity.class);

        if (trip.getTripPlaces() != null || trip.getTripPlaces().size() > 0) {
            List<TripPlaceEntity> tripPlaceEntities = objectMapper.mapList(trip.getTripPlaces(), TripPlaceEntity.class);
            tripPlaceEntities.forEach(tripPlaceEntity -> tripPlaceEntity.setTrip(tripEntity));
            tripEntity.setTripPlaces(tripPlaceEntities);
        }

        TripEntity savedTripEntity = jpaTripRepository.save(tripEntity);

        return objectMapper.map(savedTripEntity, Trip.class);
    }

    @Override
    public Optional<Trip> findByName(String name) {
        Optional<Trip> trip = jpaTripRepository.findByName(name)
                .map(entity -> objectMapper.map(entity, Trip.class));
        return trip;
    }
}
