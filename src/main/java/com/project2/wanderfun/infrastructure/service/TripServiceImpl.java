package com.project2.wanderfun.infrastructure.service;

import com.project2.wanderfun.application.exception.ObjectNotFoundException;
import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.repository.TripRepository;
import com.project2.wanderfun.application.service.TripService;
import com.project2.wanderfun.domain.model.Place;
import com.project2.wanderfun.domain.model.Trip;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripServiceImpl extends BaseServiceImpl<Trip> implements TripService {
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
        List<Trip> trips = tripRepository.findAllByUser_Id(userId);
        return trips;
    }

    @Override
    @Transactional
    public void deleteAllByUserId(Long userId) {
        tripRepository.deleteAllByUser_Id(userId);
    }
}
