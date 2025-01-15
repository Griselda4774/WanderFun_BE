package com.project2.wanderfun.application.repository;

import com.project2.wanderfun.domain.model.FavouritePlace;

import java.util.List;
import java.util.Optional;

public interface FavouritePlaceRepository extends BaseRepository<FavouritePlace, Long> {
    List<FavouritePlace> findAllByUser_Id(Long userId);
    void deleteByIds(List<Long> ids);
}
