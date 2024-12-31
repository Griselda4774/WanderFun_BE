package com.project2.wanderfun.application.usecase;

import com.project2.wanderfun.application.dto.place.PlaceCreateDto;
import com.project2.wanderfun.application.dto.place.PlaceDto;
import com.project2.wanderfun.application.exception.ObjectAlreadyExistException;
import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.service.PlaceService;
import com.project2.wanderfun.domain.model.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public List<PlaceDto> findAllPlacesByNameContaining(String name) {
        return objectMapper.mapList(placeService.findAllByNameContaining(name), PlaceDto.class);
    }

    public PlaceDto findPlaceByName(String name) {
        return objectMapper.map(placeService.findByName(name), PlaceDto.class);
    }

    public PlaceDto findPlaceByLongitudeAndLatitude(double longitude, double latitude) {
        return objectMapper.map(placeService.findByLongitudeAndLatitude(longitude, latitude), PlaceDto.class);
    }

    public boolean createPlace(PlaceCreateDto placeCreateDto) throws ObjectAlreadyExistException {
        Place place = objectMapper.map(placeCreateDto, Place.class);
        Place existingPlace;
        try {
            existingPlace = placeService.findByName(place.getName());
        } catch (Exception e) {
            existingPlace = null;
        }
        if(existingPlace != null) {
            throw new ObjectAlreadyExistException("This name is already used!");
        }

        try {
            existingPlace = placeService.findByLongitudeAndLatitude(place.getLongitude(), place.getLatitude());
        } catch (Exception e) {
            existingPlace = null;
        }
        if(existingPlace != null) {
            throw new ObjectAlreadyExistException("This longitude and latitude is already used!");
        }

        placeService.create(place);
        return true;
    }

    public boolean updatePlaceById(Long id, PlaceCreateDto placeCreateDto) throws ObjectAlreadyExistException {
        Place place = objectMapper.map(placeCreateDto, Place.class);

        Place currentPlace = placeService.findById(id);
        if (!place.getName().equals(currentPlace.getName())) {
            Place existingPlace;
            try {
                existingPlace = placeService.findByName(place.getName());
            } catch (Exception e) {
                existingPlace = null;
            }
            if (existingPlace != null) {
                throw new ObjectAlreadyExistException("This name is already used!");
            }
        }

        if (place.getLongitude() != currentPlace.getLongitude() || place.getLatitude() != currentPlace.getLatitude()) {
            Place existingPlace;
            try {
                existingPlace = placeService.findByLongitudeAndLatitude(place.getLongitude(), place.getLatitude());
            } catch (Exception e) {
                existingPlace = null;
            }
            if (existingPlace != null) {
                throw new ObjectAlreadyExistException("This longitude and latitude is already used!");
            }
        }

        placeService.updateById(id, place);
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
