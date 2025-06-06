package com.wanderfun.domainlayer.repository.place;

import com.wanderfun.domainlayer.model.places.Feedback;
import com.wanderfun.domainlayer.repository.BaseRepository;

import java.util.List;

public interface FeedbackRepository extends BaseRepository<Feedback, Long> {
    List<Feedback> findAllByPlaceId(Long placeId);
}
