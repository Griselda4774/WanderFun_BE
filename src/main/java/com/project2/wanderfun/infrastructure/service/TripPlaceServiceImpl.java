package com.project2.wanderfun.infrastructure.service;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.repository.TripPlaceRepository;
import com.project2.wanderfun.application.service.TripPlaceService;
import com.project2.wanderfun.domain.model.TripPlace;
import org.springframework.stereotype.Service;

@Service
public class TripPlaceServiceImpl extends BaseServiceImpl<TripPlace> implements TripPlaceService {

    public TripPlaceServiceImpl(TripPlaceRepository tripPlaceRepository, ObjectMapper objectMapper) {
        super(tripPlaceRepository, objectMapper, TripPlace.class);
    }
}
