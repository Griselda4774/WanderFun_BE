package com.wanderfun.applicationlayer.service;

import com.wanderfun.domainlayer.model.places.Feedback;

import java.util.List;

public interface FeedbackService extends BaseService<Feedback, Long> {
    List<Feedback> findAllByPlaceId(Long placeId);
}