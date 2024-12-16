package com.project2.wanderfun.application.usecase;

import com.project2.wanderfun.application.dto.place.PlaceCreateDto;
import com.project2.wanderfun.application.dto.trip.TripCreateDto;
import com.project2.wanderfun.application.dto.trip.TripDto;
import com.project2.wanderfun.application.exception.ObjectAlreadyExistException;
import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.service.TripService;
import com.project2.wanderfun.domain.model.Place;
import com.project2.wanderfun.domain.model.Trip;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripUsecase {
    private final TripService tripService;
    private final ObjectMapper objectMapper;

    public TripUsecase(TripService tripService, ObjectMapper objectMapper) {
        this.tripService = tripService;
        this.objectMapper = objectMapper;
    }

    public List<TripDto> findAllTrips() {
        return objectMapper.mapList(tripService.findAll(), TripDto.class);
    }

    public TripDto findTripById(Long id) {
        return objectMapper.map(tripService.findById(id), TripDto.class);
    }

    public boolean createTrip(TripCreateDto tripCreateDto) {
        Trip trip = objectMapper.map(tripCreateDto, Trip.class);
        Trip existingTrip = null;
        try {
            existingTrip = tripService.findByName(trip.getName());
        } catch (Exception e) {}

        if(existingTrip != null) {
            throw new ObjectAlreadyExistException("This name already used!");
        }

        tripService.create(trip);
        return true;
    }

    public boolean updateTripById(Long id, TripCreateDto tripCreateDto) {
        Trip trip = objectMapper.map(tripCreateDto, Trip.class);
        Trip existingTrip = null;
        try {
            existingTrip = tripService.findByName(trip.getName());
        } catch (Exception e) {}

        if(existingTrip != null) {
            throw new ObjectAlreadyExistException("This name already used!");
        }

        tripService.updateById(id, trip);
        return true;
    }

    public boolean deleteTripById(Long id) {
        tripService.deleteById(id);
        return true;
    }

    public boolean deleteAllTrips() {
        tripService.deleteAll();
        return true;
    }
}
