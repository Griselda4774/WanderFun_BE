package com.wanderfun.domainlayer.repository;

import com.wanderfun.domainlayer.model.users.FavouritePlace;

import java.util.List;

public interface FavouritePlaceRepository extends BaseRepository<FavouritePlace, Long> {
    List<FavouritePlace> findAllByUser_Id(Long userId);
    void deleteByIds(List<Long> ids);
}
