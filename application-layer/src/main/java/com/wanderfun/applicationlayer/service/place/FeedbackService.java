package com.wanderfun.applicationlayer.service.place;

import com.wanderfun.applicationlayer.service.BaseService;
import com.wanderfun.domainlayer.model.places.Feedback;

import java.util.List;

public interface FeedbackService extends BaseService<Feedback, Long> {
    List<Feedback> findAllByPlaceId(Long placeId);
}
