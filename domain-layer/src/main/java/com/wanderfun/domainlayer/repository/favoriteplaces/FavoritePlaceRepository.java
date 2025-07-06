package com.wanderfun.domainlayer.repository.favoriteplaces;

import com.wanderfun.domainlayer.model.favoriteplaces.FavoritePlace;
import com.wanderfun.domainlayer.model.places.Place;
import com.wanderfun.domainlayer.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface FavoritePlaceRepository extends BaseRepository<FavoritePlace, Long> {
    List<Place> findAllByUserId(Long userId);
    Optional<FavoritePlace> findByUserIdAndPlaceId(Long userId, Long placeId);
}
