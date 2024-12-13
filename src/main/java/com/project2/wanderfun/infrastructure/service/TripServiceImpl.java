package com.project2.wanderfun.infrastructure.service;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.repository.TripRepository;
import com.project2.wanderfun.application.service.TripService;
import com.project2.wanderfun.domain.model.Trip;
import org.springframework.stereotype.Service;

@Service
public class TripServiceImpl extends BaseServiceImpl<Trip> implements TripService {

    public TripServiceImpl(TripRepository tripRepository, ObjectMapper objectMapper) {
        super(tripRepository, objectMapper, Trip.class);
    }
}
