package com.wanderfun.applicationlayer.usecase;


import com.wanderfun.applicationlayer.dto.PlaceCategoryDto;

import com.wanderfun.applicationlayer.dto.places.PlaceDto;
import com.wanderfun.domainlayer.model.places.Place;
import com.wanderfun.domainlayer.model.places.PlaceCategory;

import java.util.List;

public interface PlaceUsecase {

    List<PlaceCategory> findAllPlaceCategory();

    PlaceCategory findPlaceCategoryById(Integer id);

    boolean createPlaceCategory(PlaceCategoryDto placeCategoryDto);

    boolean updatePlaceCategoryById(Integer id, PlaceCategoryDto placeCategoryDto);

    boolean deletePlaceCategoryById(Integer id);

    List<PlaceDto> findAllPlace();
    List<PlaceDto> findAllByNameContaining(String name);
    Place findByName(String name);
    Place findByLongitudeAndLatitude(double longitude, double latitude);
    List<Place> findAllByProvinceName(String provinceName);
    boolean createPlace(PlaceDto placeDto);
    boolean createAllPlaces(List<PlaceDto> placeDtoList);
    boolean updatePlaceById(Long id, PlaceDto placeDto);
    boolean deletePlaceById(Long id);
}
