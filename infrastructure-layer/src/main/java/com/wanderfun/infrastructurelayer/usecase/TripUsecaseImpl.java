package com.wanderfun.infrastructurelayer.usecase;

import com.wanderfun.applicationlayer.dto.trips.TripCreateDto;
import com.wanderfun.applicationlayer.dto.trips.TripDto;
import com.wanderfun.applicationlayer.exception.ObjectAlreadyExistException;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.trips.TripService;
import com.wanderfun.applicationlayer.service.users.UserService;
import com.wanderfun.applicationlayer.usecase.TripUsecase;
import com.wanderfun.applicationlayer.util.JwtUtil;
import com.wanderfun.domainlayer.model.trips.Trip;
import com.wanderfun.domainlayer.model.trips.TripPlace;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TripUsecaseImpl implements TripUsecase {
    private final TripService tripService;
    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public TripUsecaseImpl(TripService tripService, ObjectMapper objectMapper, JwtUtil jwtUtil, UserService userService) {
        this.tripService = tripService;
        this.objectMapper = objectMapper;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
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

        trip.setStartTime(trip.getTripPlaceList().getFirst().getStartTime());
        trip.setEndTime(trip.getTripPlaceList().getLast().getEndTime());

        trip.setUserId(userService.findByAccountId(jwtUtil.getIdFromToken(accessToken)).getId());
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

        trip.setStartTime(trip.getTripPlaceList().getFirst().getStartTime());
        trip.setEndTime(trip.getTripPlaceList().getLast().getEndTime());
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
