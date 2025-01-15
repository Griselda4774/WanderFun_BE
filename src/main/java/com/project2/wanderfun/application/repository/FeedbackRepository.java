package com.project2.wanderfun.application.repository;

import com.project2.wanderfun.domain.model.Feedback;

import java.util.List;

public interface FeedbackRepository extends BaseRepository<Feedback, Long> {
    List<Feedback> findAllByPlace_Id(Long placeId);
}
