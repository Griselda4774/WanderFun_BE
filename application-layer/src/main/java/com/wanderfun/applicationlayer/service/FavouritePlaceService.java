package com.wanderfun.applicationlayer.service;

import com.wanderfun.domainlayer.model.users.FavouritePlace;

import java.util.List;

public interface FavouritePlaceService extends BaseService<FavouritePlace> {
    List<FavouritePlace> findAllByUserId(Long userId);
    void deleteByIds(List<Long> ids);
}
