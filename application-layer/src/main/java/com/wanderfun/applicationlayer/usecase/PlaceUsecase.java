package com.wanderfun.applicationlayer.usecase;

import com.wanderfun.applicationlayer.dto.places.FullPlaceDto;
import com.wanderfun.applicationlayer.dto.places.PlaceCreateDto;
import com.wanderfun.applicationlayer.dto.places.PlaceDto;
import com.wanderfun.domainlayer.model.places.Feedback;

import java.util.List;

public interface PlaceUsecase {
    List<PlaceDto> findAll();
    List<PlaceDto> findAllByNameContaining(String name);
    FullPlaceDto findById(Long id);
    PlaceDto findByName(String name);
    PlaceDto findByLongitudeAndLatitude(double longitude, double latitude);
    List<PlaceDto> findAllByProvinceName(String provinceName);
    boolean create(PlaceCreateDto placeCreateDto);
    boolean createAll(List<PlaceCreateDto> placeCreateDtoList);
    boolean updateById(Long id, PlaceCreateDto placeCreateDto);
    boolean deleteById(Long id);
    boolean deleteAll();
    List<Feedback> findAllFeedbacksByPlaceId(Long placeId);
}
