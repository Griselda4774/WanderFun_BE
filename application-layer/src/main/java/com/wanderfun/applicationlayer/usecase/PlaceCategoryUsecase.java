package com.wanderfun.applicationlayer.usecase;


import com.wanderfun.applicationlayer.dto.places.PlaceCategoryDto;

import com.wanderfun.domainlayer.model.places.PlaceCategory;

import java.util.List;

public interface PlaceCategoryUsecase {

    List<PlaceCategory> findAllPlaceCategory();

    PlaceCategory findPlaceCategoryById(Long id);

    boolean createPlaceCategory(PlaceCategoryDto placeCategoryDto);

    boolean updatePlaceCategoryById(Long id, PlaceCategoryDto placeCategoryDto);

    boolean deletePlaceCategoryById(Long id);
}
