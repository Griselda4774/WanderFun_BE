package com.wanderfun.infrastructurelayer.repository.places;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.model.places.Feedback;
import com.wanderfun.domainlayer.repository.place.FeedbackRepository;
import com.wanderfun.infrastructurelayer.persistence.entity.places.FeedbackEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.places.JpaFeedbackRepository;
import com.wanderfun.infrastructurelayer.repository.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FeedbackRepositoryImpl extends BaseRepositoryImpl<Feedback, FeedbackEntity, Long> implements FeedbackRepository {
    private final JpaFeedbackRepository jpaFeedbackRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public FeedbackRepositoryImpl(JpaFeedbackRepository jpaFeedbackRepository, ObjectMapper objectMapper) {
        super(jpaFeedbackRepository, objectMapper, Feedback.class, FeedbackEntity.class);
        this.jpaFeedbackRepository = jpaFeedbackRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Feedback> findAllByPlaceId(Long placeId) {
        return objectMapper.mapList(jpaFeedbackRepository.findByPlaceId(placeId), Feedback.class);
    }
}
