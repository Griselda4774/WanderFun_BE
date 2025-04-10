package com.wanderfun.infrastructurelayer.persistence.jpaRepository;

import com.wanderfun.infrastructurelayer.persistence.entity.FeedbackEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaFeedbackRepository extends JpaBaseRepository<FeedbackEntity, Long> {
    List<FeedbackEntity> findAllByPlace_Id(Long placeId);
}
