package com.wanderfun.applicationlayer.usecase.places;


import com.wanderfun.applicationlayer.dto.PlaceCategoryDto;
import com.wanderfun.domainlayer.model.places.PlaceCategory;

import java.util.List;

public interface PlaceCategoryUsecase {

    List<PlaceCategory> findAllPlaceCategory();

    PlaceCategory findPlaceCategoryById(Integer id);

    boolean createPlaceCategory(PlaceCategoryDto placeCategoryDto);

    boolean updatePlaceCategoryById(Integer id, PlaceCategoryDto placeCategoryDto);

    boolean deletePlaceCategoryById(Integer id);
}
