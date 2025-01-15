package com.project2.wanderfun.application.service;

import com.project2.wanderfun.domain.model.Feedback;

import java.util.List;

public interface FeedbackService extends BaseService<Feedback> {
    public List<Feedback> findAllByPlaceId(Long placeId);
}