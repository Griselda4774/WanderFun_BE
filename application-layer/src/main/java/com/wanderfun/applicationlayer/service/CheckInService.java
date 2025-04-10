package com.wanderfun.applicationlayer.service;

import com.wanderfun.domainlayer.model.checkins.CheckIn;

import java.util.List;

public interface CheckInService extends BaseService<CheckIn>{
    List<CheckIn> findAllByUserId(Long userId);
    CheckIn findByPlaceIdAndUserId(Long placeId, Long userId);
}
