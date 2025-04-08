package com.project2.wanderfun.application.usecase;

import com.project2.wanderfun.application.dto.trip.TripCreateDto;
import com.project2.wanderfun.application.dto.trip.TripDto;
import com.project2.wanderfun.application.exception.ObjectAlreadyExistException;
import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.service.TripService;
import com.project2.wanderfun.application.util.JwtUtil;
import com.project2.wanderfun.domain.model.trips.Trip;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripUsecase {
    private final TripService tripService;
    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;

    public TripUsecase(TripService tripService, ObjectMapper objectMapper, JwtUtil jwtUtil) {
        this.tripService = tripService;
        this.objectMapper = objectMapper;
        this.jwtUtil = jwtUtil;
    }

    public List<TripDto> findAllTrips(String accessToken) {
        return objectMapper.mapList(tripService.findAllByUserId(jwtUtil.getIdFromToken(accessToken)), TripDto.class);
    }

    public TripDto findTripById(Long id) {
        return objectMapper.map(tripService.findById(id), TripDto.class);
    }

    public boolean createTrip(TripCreateDto tripCreateDto, String accessToken) throws ObjectAlreadyExistException {
        Trip trip = objectMapper.map(tripCreateDto, Trip.class);
        Trip existingTrip = null;
        try {
            existingTrip = tripService.findByName(trip.getName());
        } catch (Exception e) {}

        if(existingTrip != null) {
            throw new ObjectAlreadyExistException("This name is already used!");
        }

        trip.setStartTime(trip.getTripPlaces().get(0).getStartTime());
        trip.setEndTime(trip.getTripPlaces().get(trip.getTripPlaces().size() - 1).getEndTime());
        trip.setUserId(jwtUtil.getIdFromToken(accessToken));
        tripService.create(trip);
        return true;
    }

    public boolean updateTripById(Long id, TripCreateDto tripCreateDto) throws ObjectAlreadyExistException{
        Trip trip = objectMapper.map(tripCreateDto, Trip.class);

        Trip currentTrip = tripService.findById(id);
        if (!trip.getName().equals(currentTrip.getName())) {
            Trip existingTrip;
            try {
                existingTrip = tripService.findByName(trip.getName());
            } catch (Exception e) {
                existingTrip = null;
            }
            if (existingTrip != null) {
                throw new ObjectAlreadyExistException("This name is already used!");
            }
        }

        trip.setStartTime(trip.getTripPlaces().get(0).getStartTime());
        trip.setEndTime(trip.getTripPlaces().get(trip.getTripPlaces().size() - 1).getEndTime());
        tripService.updateById(id, trip);
        return true;
    }

    public boolean deleteTripById(Long id) {
        tripService.deleteById(id);
        return true;
    }

    public boolean deleteAllTrips(String accessToken) {
        tripService.deleteAllByUserId(jwtUtil.getIdFromToken(accessToken));
        return true;
    }

}
