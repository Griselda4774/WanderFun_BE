package com.project2.wanderfun.infrastructure.service;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.repository.FeedbackRepository;
import com.project2.wanderfun.application.service.FeedbackService;
import com.project2.wanderfun.domain.model.Feedback;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl extends BaseServiceImpl<Feedback> implements FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, ObjectMapper objectMapper) {
        super(feedbackRepository, objectMapper, Feedback.class);
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    @Transactional
    public List<Feedback> findAllByPlaceId(Long placeId) {
        List<Feedback> feedbacks = feedbackRepository.findAllByPlace_Id(placeId);
        return feedbacks;
    }
}

