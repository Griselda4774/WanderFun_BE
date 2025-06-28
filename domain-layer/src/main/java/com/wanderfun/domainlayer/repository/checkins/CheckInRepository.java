package com.wanderfun.domainlayer.repository.checkins;

import com.wanderfun.domainlayer.model.checkins.CheckIn;
import com.wanderfun.domainlayer.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface CheckInRepository extends BaseRepository<CheckIn, Long> {
    List<CheckIn> findAllByUserId(Long userId);
    Optional<CheckIn> findLastCheckInByUserId(Long userId);
    Long countTotalCheckInsToday();
}
