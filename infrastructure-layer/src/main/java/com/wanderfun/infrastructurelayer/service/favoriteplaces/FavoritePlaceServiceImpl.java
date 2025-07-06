package com.wanderfun.infrastructurelayer.service.favoriteplaces;

import com.wanderfun.applicationlayer.exception.ObjectNotFoundException;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.favoriteplaces.FavoritePlaceService;
import com.wanderfun.domainlayer.model.favoriteplaces.FavoritePlace;
import com.wanderfun.domainlayer.model.places.Place;
import com.wanderfun.domainlayer.repository.favoriteplaces.FavoritePlaceRepository;
import com.wanderfun.infrastructurelayer.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritePlaceServiceImpl extends BaseServiceImpl<FavoritePlace, Long> implements FavoritePlaceService {
    private final FavoritePlaceRepository favoritePlaceRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public FavoritePlaceServiceImpl(FavoritePlaceRepository favoritePlaceRepository, ObjectMapper objectMapper) {
        super(favoritePlaceRepository, objectMapper, FavoritePlace.class);
        this.favoritePlaceRepository = favoritePlaceRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Place> findAllByUserId(Long userId) {
        return favoritePlaceRepository.findAllByUserId(userId);
    }

    @Override
    public FavoritePlace findFavoritePlaceByUserIdAndPlaceId(Long userId, Long placeId) {
        return favoritePlaceRepository.findByUserIdAndPlaceId(userId, placeId)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Favorite place not found for user with ID: %d and place with ID: %d", userId, placeId)));
    }
}
