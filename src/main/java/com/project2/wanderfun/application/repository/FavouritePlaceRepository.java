package com.project2.wanderfun.application.repository;

import com.project2.wanderfun.domain.model.users.FavouritePlace;

import java.util.List;

public interface FavouritePlaceRepository extends BaseRepository<FavouritePlace, Long> {
    List<FavouritePlace> findAllByUser_Id(Long userId);
    void deleteByIds(List<Long> ids);
}
