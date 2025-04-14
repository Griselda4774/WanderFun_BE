//package com.wanderfun.infrastructurelayer.repository;
//
//import com.wanderfun.applicationlayer.mapper.ObjectMapper;
//import com.wanderfun.domainlayer.repository.TripRepository;
//import com.wanderfun.domainlayer.model.trips.Trip;
//import com.wanderfun.infrastructurelayer.persistence.entity.*;
//import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaTripRepository;
//import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class TripRepositoryImpl extends BaseRepositoryImpl<Trip, TripEntity, Long> implements TripRepository {
//    private final JpaTripRepository jpaTripRepository;
//    private final JpaUserRepository jpaUserRepository;
//
//    @Autowired
//    public TripRepositoryImpl(JpaTripRepository jpaTripRepository, ObjectMapper objectMapper, JpaUserRepository jpaUserRepository) {
//        super(jpaTripRepository, objectMapper, Trip.class, TripEntity.class);
//        this.jpaTripRepository = jpaTripRepository;
//        this.jpaUserRepository = jpaUserRepository;
//    }
//
//    @Override
//    public Trip save(Trip trip) {
//        TripEntity tripEntity = objectMapper.map(trip, TripEntity.class);
//
//        if (trip.getTripPlaces() != null || trip.getTripPlaces().size() > 0) {
//            List<TripPlaceEntity> tripPlaceEntities = objectMapper.mapList(trip.getTripPlaces(), TripPlaceEntity.class);
//            tripPlaceEntities.forEach(tripPlaceEntity -> tripPlaceEntity.setTrip(tripEntity));
//            tripEntity.setTripPlaces(tripPlaceEntities);
//        }
//
//        UserEntity userEntity = jpaUserRepository.findById(trip.getUserId()).get();
//        tripEntity.setUser(userEntity);
//
//        TripEntity savedTripEntity = jpaTripRepository.save(tripEntity);
//
//        return objectMapper.map(savedTripEntity, Trip.class);
//    }
//
//    @Override
//    public Optional<Trip> findByName(String name) {
//        Optional<Trip> trip = jpaTripRepository.findByName(name)
//                .map(entity -> objectMapper.map(entity, Trip.class));
//        return trip;
//    }
//
//    @Override
//    public List<Trip> findAllByUser_Id(Long userId) {
//        List<Trip> trips = objectMapper.mapList(jpaTripRepository.findAllByUser_Id(userId), Trip.class);
//        return trips;
//    }
//
//    @Override
//    public void deleteAllByUser_Id(Long userId) {
//        jpaTripRepository.deleteAllByUser_Id(userId);
//    }
//}
