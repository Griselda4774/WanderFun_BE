package com.project2.wanderfun.application.service;

import com.project2.wanderfun.domain.model.FavouritePlace;

import java.util.List;

public interface FavouritePlaceService extends BaseService<FavouritePlace> {
    List<FavouritePlace> findAllByUserId(Long userId);
    void deleteByIds(List<Long> ids);
}
