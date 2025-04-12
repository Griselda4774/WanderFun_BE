//package com.wanderfun.infrastructurelayer.repository;
//
//import com.wanderfun.applicationlayer.mapper.ObjectMapper;
//import com.wanderfun.domainlayer.repository.FeedbackRepository;
//import com.wanderfun.domainlayer.model.places.Feedback;
//import com.wanderfun.infrastructurelayer.persistence.entity.*;
//import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaFeedbackRepository;
//import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaPlaceRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class FeedbackRepositoryImpl extends BaseRepositoryImpl<Feedback, FeedbackEntity, Long> implements FeedbackRepository {
//    private final JpaFeedbackRepository jpaFeedbackRepository;
//    private final JpaPlaceRepository jpaPlaceRepository;
//
//    @Autowired
//    public FeedbackRepositoryImpl(JpaFeedbackRepository jpaFeedbackRepository, ObjectMapper objectMapper, JpaPlaceRepository jpaPlaceRepository) {
//        super(jpaFeedbackRepository, objectMapper, Feedback.class, FeedbackEntity.class);
//        this.jpaFeedbackRepository = jpaFeedbackRepository;
//        this.jpaPlaceRepository = jpaPlaceRepository;
//    }
//
//    @Override
//    public Feedback save(Feedback feedback) {
//        FeedbackEntity feedbackEntity = objectMapper.map(feedback, FeedbackEntity.class);
//
//       PlaceEntity placeEntity = jpaPlaceRepository.findById(feedback.getPlaceId()).get();
//        feedbackEntity.setPlace(placeEntity);
//
//        FeedbackEntity savedFeedbackEntity = jpaFeedbackRepository.save(feedbackEntity);
//
//        return objectMapper.map(savedFeedbackEntity, Feedback.class);
//    }
//
//    @Override
//    public List<Feedback> findAllByPlace_Id(Long placeId) {
//        List<Feedback> feedbacks = objectMapper.mapList(jpaFeedbackRepository.findAllByPlace_Id(placeId), Feedback.class);
//        return feedbacks;
//    }
//}
