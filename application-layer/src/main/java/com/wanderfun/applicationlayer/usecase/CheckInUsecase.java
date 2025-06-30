package com.wanderfun.applicationlayer.usecase;

import com.wanderfun.applicationlayer.dto.checkins.CheckInDto;
import com.wanderfun.applicationlayer.dto.places.MiniPlaceDto;

import java.util.List;

public interface CheckInUsecase {
    CheckInDto create(String accessToken, Long placeId);
    List<CheckInDto> findAllByUser(String accessToken);
    List<MiniPlaceDto> findAllEligiblePlaces(double userLng, double userLat);
}
