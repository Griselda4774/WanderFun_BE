package com.wanderfun.infrastructurelayer.repository.favoriteplaces;


import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.model.favoriteplaces.FavoritePlace;
import com.wanderfun.domainlayer.model.places.Place;
import com.wanderfun.domainlayer.repository.favoriteplaces.FavoritePlaceRepository;
import com.wanderfun.infrastructurelayer.persistence.entity.favoriteplace.FavoritePlaceEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.favoriteplaces.JpaFavoritePlaceRepository;
import com.wanderfun.infrastructurelayer.repository.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FavoritePlaceRepositoryImpl extends BaseRepositoryImpl<FavoritePlace, FavoritePlaceEntity, Long> implements FavoritePlaceRepository {
    private final JpaFavoritePlaceRepository jpaFavoritePlaceRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public FavoritePlaceRepositoryImpl(JpaFavoritePlaceRepository jpaFavoritePlaceRepository, ObjectMapper objectMapper) {
        super(jpaFavoritePlaceRepository, objectMapper, FavoritePlace.class, FavoritePlaceEntity.class);
        this.jpaFavoritePlaceRepository = jpaFavoritePlaceRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Place> findAllByUserId(Long userId) {
        return objectMapper.mapList(jpaFavoritePlaceRepository.findAllByUserId(userId), Place.class);
    }

    @Override
    public Optional<FavoritePlace> findByUserIdAndPlaceId(Long userId, Long placeId) {
        return jpaFavoritePlaceRepository.findByUserIdAndPlaceId(userId, placeId)
                .map(favoritePlaceEntity -> objectMapper.map(favoritePlaceEntity, FavoritePlace.class));
    }
}
