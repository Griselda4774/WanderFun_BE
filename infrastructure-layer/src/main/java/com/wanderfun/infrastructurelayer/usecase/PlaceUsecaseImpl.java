package com.wanderfun.infrastructurelayer.usecase;

import com.wanderfun.applicationlayer.service.place.PlaceCategoryService;
import com.wanderfun.applicationlayer.usecase.PlaceUsecase;
import com.wanderfun.domainlayer.model.places.PlaceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaceUsecaseImpl implements PlaceUsecase {
     private final PlaceCategoryService placeCategoryService;

     @Autowired
     public PlaceUsecaseImpl(PlaceCategoryService placeCategoryService) {
         this.placeCategoryService = placeCategoryService;
     }

    @Override
    public void createPlaceCategory(PlaceCategory placeCategory) {
        placeCategoryService.create(placeCategory);
    }
}
