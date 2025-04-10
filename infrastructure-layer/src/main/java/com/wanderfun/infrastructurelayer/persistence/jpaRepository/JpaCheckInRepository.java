package com.wanderfun.infrastructurelayer.persistence.jpaRepository;

import com.wanderfun.infrastructurelayer.persistence.entity.CheckInEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaCheckInRepository extends JpaBaseRepository<CheckInEntity, Long> {
    List<CheckInEntity> findAllByUser_Id(Long userId);
    Optional<CheckInEntity> findByPlaceIdAndUser_Id(Long placeId, Long userId);
}
