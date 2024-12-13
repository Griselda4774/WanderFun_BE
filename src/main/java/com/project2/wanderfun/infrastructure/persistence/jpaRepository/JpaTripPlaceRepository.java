package com.project2.wanderfun.infrastructure.persistence.jpaRepository;

import com.project2.wanderfun.infrastructure.persistence.entity.TripPlaceEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTripPlaceRepository extends JpaBaseRepository<TripPlaceEntity, Long> {
}
