package com.wanderfun.infrastructurelayer.service.places;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.place.FeedbackService;
import com.wanderfun.domainlayer.model.places.Feedback;
import com.wanderfun.domainlayer.repository.place.FeedbackRepository;
import com.wanderfun.infrastructurelayer.service.BaseServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl extends BaseServiceImpl<Feedback, Long> implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, ObjectMapper objectMapper) {
        super(feedbackRepository, objectMapper, Feedback.class);
        this.feedbackRepository = feedbackRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    @Transactional
    public List<Feedback> findAllByPlaceId(Long placeId) {
        return feedbackRepository.findAllByPlaceId(placeId);
    }
}
