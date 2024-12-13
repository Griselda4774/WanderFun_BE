package com.project2.wanderfun.infrastructure.persistence.jpaRepository;

import com.project2.wanderfun.infrastructure.persistence.entity.TripEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTripRepository extends JpaBaseRepository<TripEntity, Long> {
}
