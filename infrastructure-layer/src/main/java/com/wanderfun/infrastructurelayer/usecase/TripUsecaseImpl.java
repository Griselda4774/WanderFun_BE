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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TripUsecaseImpl implements TripUsecase {
    private final TripService tripService;
    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Autowired
    public TripUsecaseImpl(TripService tripService, ObjectMapper objectMapper, JwtUtil jwtUtil, UserService userService) {
        this.tripService = tripService;
        this.objectMapper = objectMapper;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    public List<TripDto> findAllTrips(String accessToken) {
        return objectMapper.mapList(tripService.findAllByUserId(userService.findByAccountId(jwtUtil.getIdFromToken(accessToken)).getId()), TripDto.class);
    }

    @Override
    public TripDto findTripById(Long id) {
        return objectMapper.map(tripService.findById(id), TripDto.class);
    }

    @Override
    public boolean createTrip(TripCreateDto tripCreateDto, String accessToken) throws ObjectAlreadyExistException {
        Trip trip = objectMapper.map(tripCreateDto, Trip.class);
        Trip existingTrip = null;
        Long createTripUserId = jwtUtil.getIdFromToken(accessToken);
        try {
            existingTrip = tripService.findByName(trip.getName());
        } catch (Exception e) {}

        if(existingTrip != null && existingTrip.getUserId().equals(createTripUserId)) {
            throw new ObjectAlreadyExistException("This name is already used!");
        }

        trip.setStartTime(trip.getTripPlaceList().getFirst().getStartTime());
        trip.setEndTime(trip.getTripPlaceList().getLast().getEndTime());

        trip.setUserId(userService.findByAccountId(jwtUtil.getIdFromToken(accessToken)).getId());
        tripService.create(trip);
        return true;
    }

    @Override
    public boolean updateTripById(Long id, TripCreateDto tripCreateDto, String accessToken) throws ObjectAlreadyExistException{
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

        trip.setUserId(userService.findByAccountId(jwtUtil.getIdFromToken(accessToken)).getId());
        tripService.updateById(id, trip);
        return true;
    }

    @Override
    public boolean deleteTripById(Long id) {
        tripService.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteAllTrips(String accessToken) {
        tripService.deleteAllByUserId(userService.findByAccountId(jwtUtil.getIdFromToken(accessToken)).getId());
        return true;
    }

}
