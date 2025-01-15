package com.project2.wanderfun.infrastructure.persistence.jpaRepository;

import com.project2.wanderfun.infrastructure.persistence.entity.FeedbackEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaFeedbackRepository extends JpaBaseRepository<FeedbackEntity, Long> {
    List<FeedbackEntity> findAllByPlace_Id(Long placeId);
}
