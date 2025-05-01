package com.wanderfun.applicationlayer.usecase.trips;

import com.wanderfun.applicationlayer.dto.trips.TripDto;

import java.util.List;

public interface TripUsecase {
    List<TripDto> findAllTrips(String accessToken);
    TripDto findTripById(Long id);
    boolean createTrip(TripDto tripDto, String accessToken);
    boolean updateTripById(Long id, TripDto tripDto);
    boolean deleteTripById(Long id);
    boolean deleteAllTrips(String accessToken);
}
