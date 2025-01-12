package com.project2.wanderfun.infrastructure.persistence.jpaRepository;

import com.project2.wanderfun.infrastructure.persistence.entity.TripEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaTripRepository extends JpaBaseRepository<TripEntity, Long> {
    Optional<TripEntity> findByName(String name);
    List<TripEntity> findAllByUser_Id(Long userId);
    void deleteAllByUser_Id(Long userId);
}
