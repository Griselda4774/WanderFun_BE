package com.wanderfun.applicationlayer.usecase;

import com.wanderfun.applicationlayer.dto.PlaceCategoryCreateDto;
import com.wanderfun.domainlayer.model.places.PlaceCategory;

import java.util.List;

public interface PlaceUsecase {

    List<PlaceCategory> findAllPlaceCategory();

    PlaceCategory findPlaceCategoryById(Long id);

    boolean createPlaceCategory(PlaceCategoryCreateDto placeCategoryCreateDto);

    boolean updatePlaceCategoryById(Long id, PlaceCategoryCreateDto placeCategoryCreateDto);

    boolean deletePlaceCategoryById(Long id);
}
