package com.project2.wanderfun.infrastructure.persistence.jpaRepository;

import com.project2.wanderfun.infrastructure.persistence.entity.CheckInEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaCheckInRepository extends JpaBaseRepository<CheckInEntity, Long> {
    List<CheckInEntity> findAllByUser_Id(Long userId);
    Optional<CheckInEntity> findByPlaceId(Long placeId);
}
