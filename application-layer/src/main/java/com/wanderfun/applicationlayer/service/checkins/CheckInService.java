package com.wanderfun.applicationlayer.service.checkins;

import com.wanderfun.applicationlayer.service.BaseService;
import com.wanderfun.domainlayer.model.checkins.CheckIn;

import java.util.List;

public interface CheckInService extends BaseService<CheckIn, Long> {
    List<CheckIn> findAllByUserId(Long userId);
    CheckIn findLastCheckInByUserIdAndPlaceId(Long userId, Long placeId);
    Long countTotalCheckInsToday();
}
