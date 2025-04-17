package com.wanderfun.applicationlayer.usecase.places;

import com.wanderfun.applicationlayer.dto.places.PlaceCreateDto;
import com.wanderfun.applicationlayer.dto.places.PlaceDto;

import java.util.List;

public interface PlaceUsecase {
    List<PlaceDto> findAll();
    List<PlaceDto> findAllByNameContaining(String name);
    PlaceDto findById(long id);
    PlaceDto findByName(String name);
    PlaceDto findByLongitudeAndLatitude(double longitude, double latitude);
    List<PlaceDto> findAllByProvinceName(String provinceName);
    boolean create(PlaceCreateDto placeCreateDto);
    boolean createAll(List<PlaceCreateDto> placeCreateDtoList);
    boolean updateById(Long id, PlaceCreateDto placeCreateDto);
    boolean deleteById(Long id);
}
