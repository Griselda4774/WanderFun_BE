package com.wanderfun.applicationlayer.usecase;

import com.wanderfun.applicationlayer.dto.favoriteplaces.FavoritePlaceDto;
import com.wanderfun.applicationlayer.dto.places.PlaceDto;

import java.util.List;

public interface FavoritePlaceUsecase {
    FavoritePlaceDto create(String accessToken, Long placeId);
    List<PlaceDto> findAllByUser(String accessToken);
    boolean deleteById(String accessToken, Long favoritePlaceId);
}
