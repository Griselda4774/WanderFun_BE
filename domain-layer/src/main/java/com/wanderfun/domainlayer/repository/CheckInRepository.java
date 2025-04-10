package com.wanderfun.domainlayer.repository;

import com.wanderfun.domainlayer.model.checkins.CheckIn;

import java.util.List;
import java.util.Optional;

public interface CheckInRepository extends BaseRepository<CheckIn, Long> {
    List<CheckIn> findAllByUser_Id(Long userId);
    Optional<CheckIn> findByPlaceIdAndUser_Id(Long placeId, Long userId);
}
