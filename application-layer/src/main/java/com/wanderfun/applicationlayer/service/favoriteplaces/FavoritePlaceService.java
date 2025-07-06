package com.wanderfun.applicationlayer.service.favoriteplaces;

import com.wanderfun.applicationlayer.service.BaseService;
import com.wanderfun.domainlayer.model.favoriteplaces.FavoritePlace;
import com.wanderfun.domainlayer.model.places.Place;

import java.util.List;

public interface FavoritePlaceService extends BaseService<FavoritePlace, Long> {
    List<Place> findAllByUserId(Long userId);
    FavoritePlace findFavoritePlaceByUserIdAndPlaceId(Long userId, Long placeId);
}
