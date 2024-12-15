package com.project2.wanderfun.infrastructure.persistence.jpaRepository;

import com.project2.wanderfun.infrastructure.persistence.entity.PlaceEntity;
import com.project2.wanderfun.infrastructure.persistence.entity.TripEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaTripRepository extends JpaBaseRepository<TripEntity, Long> {
    Optional<TripEntity> findByName(String name);

}
