package com.wanderfun.infrastructurelayer.persistence.jpaRepository.checkins;

import com.wanderfun.infrastructurelayer.persistence.entity.checkins.CheckInEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaCheckInRepository extends JpaBaseRepository<CheckInEntity, Long> {
    @Query("SELECT c FROM CheckInEntity c WHERE c.user.id = :user_id")
    List<CheckInEntity> findAllByUserId(@Param("user_id") Long userId);

    @Query("SELECT c FROM CheckInEntity c WHERE c.user.id = :user_id ORDER BY c.createdAt DESC LIMIT 1")
    Optional<CheckInEntity> findLastCheckInByUserId(@Param("user_id") Long userId);

    @Query("SELECT COUNT(c) FROM CheckInEntity c WHERE DATE(c.createdAt) = CURRENT_DATE")
    Long countTotalCheckInsToday();
}
