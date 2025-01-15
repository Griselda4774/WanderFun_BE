package com.project2.wanderfun.infrastructure.persistence.jpaRepository;

import com.project2.wanderfun.infrastructure.persistence.entity.CheckInEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaCheckInRepository extends JpaBaseRepository<CheckInEntity, Long> {
    List<CheckInEntity> findAllByUser_Id(Long userId);
}
