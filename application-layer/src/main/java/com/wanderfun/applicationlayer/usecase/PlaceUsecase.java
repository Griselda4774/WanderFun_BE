package com.wanderfun.applicationlayer.usecase;

import com.wanderfun.applicationlayer.dto.places.*;

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
    List<FeedbackDto> findAllFeedbacksByPlaceId(Long placeId);
    FeedbackDto createFeedback(String accessToken, Long placeId, FeedbackCreateDto feedbackCreateDto);
    FeedbackDto updateFeedbackById(String accessToken, Long id, FeedbackCreateDto feedbackCreateDto);
    boolean deleteFeedbackById(String accessToken, Long id);
}
