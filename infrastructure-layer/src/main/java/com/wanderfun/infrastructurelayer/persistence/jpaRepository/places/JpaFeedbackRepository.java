package com.wanderfun.infrastructurelayer.persistence.jpaRepository.places;


import com.wanderfun.infrastructurelayer.persistence.entity.places.FeedbackEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaFeedbackRepository extends JpaBaseRepository<FeedbackEntity, Long> {
    @Query("SELECT f FROM FeedbackEntity f WHERE f.place.id = :place_id")
    List<FeedbackEntity> findByPlaceId(@Param("place_id")Long placeId);
}
