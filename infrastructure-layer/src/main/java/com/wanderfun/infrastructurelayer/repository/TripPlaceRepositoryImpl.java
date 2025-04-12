//package com.wanderfun.infrastructurelayer.repository;
//
//import com.wanderfun.applicationlayer.mapper.ObjectMapper;
//import com.wanderfun.domainlayer.repository.TripPlaceRepository;
//import com.wanderfun.domainlayer.model.trips.TripPlace;
//import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaTripPlaceRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class TripPlaceRepositoryImpl extends BaseRepositoryImpl<TripPlace, TripPlaceEntity, Long> implements TripPlaceRepository {
//
//    public TripPlaceRepositoryImpl(JpaTripPlaceRepository jpaTripPlaceRepository, ObjectMapper objectMapper) {
//        super(jpaTripPlaceRepository, objectMapper, TripPlace.class, TripPlaceEntity.class);
//    }
//
//}
