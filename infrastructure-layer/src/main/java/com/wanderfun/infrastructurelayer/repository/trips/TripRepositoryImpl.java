package com.wanderfun.infrastructurelayer.repository.trips;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.repository.trip.TripRepository;
import com.wanderfun.domainlayer.model.trips.Trip;
import com.wanderfun.infrastructurelayer.persistence.entity.trips.TripEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.trips.TripPlaceEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.users.UserEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.trips.JpaTripRepository;
import com.wanderfun.infrastructurelayer.repository.BaseRepositoryImpl;
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

        if (trip.getTripPlaceList() != null && !trip.getTripPlaceList().isEmpty()) {
            List<TripPlaceEntity> tripPlaceEntities = objectMapper.mapList(trip.getTripPlaceList(), TripPlaceEntity.class);
            tripPlaceEntities.forEach(tripPlaceEntity -> tripPlaceEntity.setTrip(tripEntity));
            tripEntity.setTripPlaceList(tripPlaceEntities);
        }

        tripEntity.setUser(new UserEntity());
        tripEntity.getUser().setId(trip.getUserId());

        TripEntity savedTripEntity = jpaTripRepository.save(tripEntity);

        return objectMapper.map(savedTripEntity, Trip.class);
    }

    @Override
    public Optional<Trip> findByName(String name) {
        return jpaTripRepository.findByName(name)
                .map(entity -> objectMapper.map(entity, Trip.class));
    }

    @Override
    public List<Trip> findAllByUserId(Long userId) {
        return objectMapper.mapList(jpaTripRepository.findAllByUserId(userId), Trip.class);
    }

    @Override
    public void deleteAllByUserId(Long userId) {
        jpaTripRepository.deleteAllByUserId(userId);
    }
}
