package com.wanderfun.applicationlayer.usecase;

import com.wanderfun.applicationlayer.dto.favoriteplaces.FavoritePlaceDto;
import com.wanderfun.applicationlayer.dto.places.PlaceDto;

import java.util.List;

public interface FavoritePlaceUsecase {
    List<PlaceDto> findAllByUser(String accessToken);
    PlaceDto create(String accessToken, Long placeId);
    PlaceDto deleteById(String accessToken, Long favoritePlaceId);
}
