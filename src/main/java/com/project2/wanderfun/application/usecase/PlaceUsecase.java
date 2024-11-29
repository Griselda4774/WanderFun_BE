package com.project2.wanderfun.application.usecase;

import com.project2.wanderfun.application.dto.PlaceDto;
import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.service.PlaceService;
import com.project2.wanderfun.domain.model.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceUsecase {
    private final PlaceService placeService;
    private final ObjectMapper objectMapper;

    @Autowired
    public PlaceUsecase(PlaceService placeService, ObjectMapper objectMapper) {
        this.placeService = placeService;
        this.objectMapper = objectMapper;
    }

    public List<PlaceDto> findAllPlaces() {
        return objectMapper.mapList(placeService.findAll(), PlaceDto.class);
    }

    public PlaceDto findPlaceById(Long id) {
        return objectMapper.map(placeService.findById(id), PlaceDto.class);
    }

    public boolean createPlace(PlaceDto placeDto) {
        placeService.create(objectMapper.map(placeDto, Place.class));
        return true;
    }

    public boolean updatePlaceById(Long id, PlaceDto placeDto) {
        placeService.updateById(id, objectMapper.map(placeDto, Place.class));
        return true;
    }

    public boolean deletePlaceById(Long id) {
        placeService.deleteById(id);
        return true;
    }

    public boolean deleteAllPlaces() {
        placeService.deleteAll();
        return true;
    }
}
