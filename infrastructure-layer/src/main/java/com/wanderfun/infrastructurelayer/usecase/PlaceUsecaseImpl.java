package com.wanderfun.infrastructurelayer.usecase;

import com.wanderfun.applicationlayer.dto.places.PlaceCategoryCreateDto;
import com.wanderfun.applicationlayer.exception.ObjectAlreadyExistException;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.place.PlaceCategoryService;
import com.wanderfun.applicationlayer.usecase.PlaceUsecase;
import com.wanderfun.domainlayer.model.places.PlaceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceUsecaseImpl implements PlaceUsecase {
     private final PlaceCategoryService placeCategoryService;
     private final ObjectMapper objectMapper;

     @Autowired
     public PlaceUsecaseImpl(PlaceCategoryService placeCategoryService, ObjectMapper objectMapper) {
         this.placeCategoryService = placeCategoryService;
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
    public boolean createPlaceCategory(PlaceCategoryCreateDto placeCategoryCreateDto) throws ObjectAlreadyExistException {
        PlaceCategory placeCategory = objectMapper.map(placeCategoryCreateDto ,PlaceCategory.class);
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
    public boolean updatePlaceCategoryById(Long id, PlaceCategoryCreateDto placeCategoryCreateDto) throws ObjectAlreadyExistException {
        PlaceCategory placeCategory = objectMapper.map(placeCategoryCreateDto, PlaceCategory.class);

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
    public boolean deletePlaceCategoryById(Long id) {
        placeCategoryService.deleteById(id);
        return true;
    }
}
