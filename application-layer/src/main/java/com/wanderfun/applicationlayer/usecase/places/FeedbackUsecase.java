package com.wanderfun.applicationlayer.usecase.places;

import com.wanderfun.applicationlayer.dto.places.FeedbackCreateDto;
import com.wanderfun.applicationlayer.dto.places.FeedbackDto;

import java.util.List;

public interface FeedbackUsecase {
    List<FeedbackDto> findAllByPlaceId(Long placeId);
    FeedbackDto create(String accessToken, Long placeId, FeedbackCreateDto feedbackCreateDto);
    FeedbackDto updateById(String accessToken, Long id, FeedbackCreateDto feedbackCreateDto);
    boolean deleteById(String accessToken, Long id);
}
