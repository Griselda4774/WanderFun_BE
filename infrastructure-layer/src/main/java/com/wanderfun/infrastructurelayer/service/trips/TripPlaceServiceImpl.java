package com.wanderfun.infrastructurelayer.service.trips;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.repository.trip.TripPlaceRepository;
import com.wanderfun.applicationlayer.service.trips.TripPlaceService;
import com.wanderfun.domainlayer.model.trips.TripPlace;
import com.wanderfun.infrastructurelayer.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TripPlaceServiceImpl extends BaseServiceImpl<TripPlace, Long> implements TripPlaceService {

    public TripPlaceServiceImpl(TripPlaceRepository tripPlaceRepository, ObjectMapper objectMapper) {
        super(tripPlaceRepository, objectMapper, TripPlace.class);
    }
}
