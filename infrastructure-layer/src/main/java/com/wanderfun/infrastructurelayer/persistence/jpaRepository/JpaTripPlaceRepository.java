package com.wanderfun.infrastructurelayer.persistence.jpaRepository;

import com.wanderfun.infrastructurelayer.persistence.entity.TripPlaceEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTripPlaceRepository extends JpaBaseRepository<TripPlaceEntity, Long> {
}
