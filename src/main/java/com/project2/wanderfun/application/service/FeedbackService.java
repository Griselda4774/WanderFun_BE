package com.project2.wanderfun.application.service;

import com.project2.wanderfun.domain.model.places.Feedback;

import java.util.List;

public interface FeedbackService extends BaseService<Feedback> {
    List<Feedback> findAllByPlaceId(Long placeId);
}