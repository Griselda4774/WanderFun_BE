package com.wanderfun.infrastructurelayer.persistence.jpaRepository.trips;

import com.wanderfun.infrastructurelayer.persistence.entity.trips.TripPlaceEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTripPlaceRepository extends JpaBaseRepository<TripPlaceEntity, Long> {
}
