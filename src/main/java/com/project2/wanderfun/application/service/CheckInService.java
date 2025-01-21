package com.project2.wanderfun.application.service;

import com.project2.wanderfun.domain.model.CheckIn;

import java.util.List;

public interface CheckInService extends BaseService<CheckIn>{
    List<CheckIn> findAllByUserId(Long userId);
    CheckIn findByPlaceIdAndUserId(Long placeId, Long userId);
}
