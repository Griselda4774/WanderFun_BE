//package com.wanderfun.infrastructurelayer.service;
//
//import com.wanderfun.applicationlayer.mapper.ObjectMapper;
//import com.wanderfun.domainlayer.repository.FeedbackRepository;
//import com.wanderfun.applicationlayer.service.FeedbackService;
//import com.wanderfun.domainlayer.model.places.Feedback;
//import jakarta.transaction.Transactional;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class FeedbackServiceImpl extends BaseServiceImpl<Feedback> implements FeedbackService {
//    private final FeedbackRepository feedbackRepository;
//
//    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, ObjectMapper objectMapper) {
//        super(feedbackRepository, objectMapper, Feedback.class);
//        this.feedbackRepository = feedbackRepository;
//    }
//
//    @Override
//    @Transactional
//    public List<Feedback> findAllByPlaceId(Long placeId) {
//        List<Feedback> feedbacks = feedbackRepository.findAllByPlace_Id(placeId);
//        return feedbacks;
//    }
//}
//
