package com.project2.wanderfun.application.repository;

import com.project2.wanderfun.domain.model.users.CheckIn;

import java.util.List;
import java.util.Optional;

public interface CheckInRepository extends BaseRepository<CheckIn, Long> {
    List<CheckIn> findAllByUser_Id(Long userId);
    Optional<CheckIn> findByPlaceIdAndUser_Id(Long placeId, Long userId);
}
