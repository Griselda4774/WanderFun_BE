package com.wanderfun.infrastructurelayer.service.trips;

import com.wanderfun.applicationlayer.exception.ObjectNotFoundException;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.repository.trip.TripRepository;
import com.wanderfun.applicationlayer.service.trips.TripService;
import com.wanderfun.domainlayer.model.trips.Trip;
import com.wanderfun.infrastructurelayer.service.BaseServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripServiceImpl extends BaseServiceImpl<Trip, Long> implements TripService {
    private final TripRepository tripRepository;

    public TripServiceImpl(TripRepository tripRepository, ObjectMapper objectMapper) {
        super(tripRepository, objectMapper, Trip.class);
        this.tripRepository = tripRepository;
    }

    @Override
    @Transactional
    public Trip findByName(String name) {
        return tripRepository.findByName(name)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("%s not found", Trip.class.getSimpleName())));
    }

    @Override
    @Transactional
    public List<Trip> findAllByUserId(Long userId) {
        return tripRepository.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public void deleteAllByUserId(Long userId) {
        tripRepository.deleteAllByUserId(userId);
    }
}
