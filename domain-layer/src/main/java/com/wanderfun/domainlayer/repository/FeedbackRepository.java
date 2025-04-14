package com.wanderfun.domainlayer.repository;

import com.wanderfun.domainlayer.model.places.Feedback;

import java.util.List;

public interface FeedbackRepository extends BaseRepository<Feedback, Long> {
    List<Feedback> findAllByPlace_Id(Long placeId);
}
