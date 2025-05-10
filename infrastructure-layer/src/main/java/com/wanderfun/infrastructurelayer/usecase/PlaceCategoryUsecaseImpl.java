package com.wanderfun.infrastructurelayer.usecase;


import com.wanderfun.applicationlayer.dto.places.PlaceCategoryDto;
import com.wanderfun.applicationlayer.exception.NotHavePermissionException;
import com.wanderfun.applicationlayer.exception.ObjectAlreadyExistException;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.place.PlaceCategoryService;
import com.wanderfun.applicationlayer.service.place.PlaceService;
import com.wanderfun.applicationlayer.usecase.PlaceCategoryUsecase;
import com.wanderfun.domainlayer.model.places.Place;
import com.wanderfun.domainlayer.model.places.PlaceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PlaceCategoryUsecaseImpl implements PlaceCategoryUsecase {
     private final PlaceCategoryService placeCategoryService;
     private final PlaceService placeService;
     private final ObjectMapper objectMapper;

     @Autowired
     public PlaceCategoryUsecaseImpl(PlaceCategoryService placeCategoryService, PlaceService placeService,ObjectMapper objectMapper) {
         this.placeCategoryService = placeCategoryService;
         this.placeService = placeService;
         this.objectMapper = objectMapper;
     }

    @Override
    public List<PlaceCategory> findAllPlaceCategory(){
        return placeCategoryService.findAll();
    }

    @Override
    public PlaceCategory findPlaceCategoryById(Long id) {
        return placeCategoryService.findById(id);
    }


    @Override
    public boolean createPlaceCategory(PlaceCategoryDto placeCategoryDto) throws ObjectAlreadyExistException {
        PlaceCategory placeCategory = objectMapper.map(placeCategoryDto,PlaceCategory.class);
        PlaceCategory existingPlaceCategory = null;
        try {
            existingPlaceCategory = placeCategoryService.findByName(placeCategory.getName());
        } catch (Exception e) {}

        if (existingPlaceCategory != null) {
            throw new ObjectAlreadyExistException("This name is already used!");
        }

        placeCategoryService.create(placeCategory);
        return true;
    }

    @Override
    public boolean updatePlaceCategoryById(Long id, PlaceCategoryDto placeCategoryDto) throws ObjectAlreadyExistException {
        PlaceCategory placeCategory = objectMapper.map(placeCategoryDto, PlaceCategory.class);

        PlaceCategory currentPlaceCategory = placeCategoryService.findById(id);
        if (!placeCategory.getName().equals(currentPlaceCategory.getName())) {
            PlaceCategory existingPlaceCategory;
            try {
                existingPlaceCategory = placeCategoryService.findByName(placeCategory.getName());
            } catch (Exception e) {
                existingPlaceCategory = null;
            }

            if (existingPlaceCategory != null) {
                throw new ObjectAlreadyExistException("This name is already used!");
            }
        }
        if (!placeCategory.getNameEn().equals(currentPlaceCategory.getNameEn())) {
            PlaceCategory existingPlaceCategory;
            try {
                existingPlaceCategory = placeCategoryService.findByNameEn(placeCategory.getNameEn());
            } catch (Exception e) {
                existingPlaceCategory = null;
            }

            if (existingPlaceCategory != null) {
                throw new ObjectAlreadyExistException("This name is already used!");
            }
        }

        placeCategoryService.updateById(id, placeCategory);
        return true;
    }

    @Override
    public boolean deletePlaceCategoryById(Long id) throws NotHavePermissionException {
         // Check if the category is "Other"
         if(Objects.equals(placeCategoryService.findById(id).getNameEn(), "Other")) {
             throw new NotHavePermissionException("You don't have permission to delete this place category");
         }

         // Check if the category is used by any places
         List<Place> placesWithCategoryList = placeService.findAllByCategoryId(id);
         if (!placesWithCategoryList.isEmpty()) {
            throw new NotHavePermissionException("You can't delete this place category because it is used by some places");
         }

         placeCategoryService.deleteById(id);
         return true;
    }
}
