package com.wanderfun.infrastructurelayer.usecase;

import com.wanderfun.applicationlayer.dto.favoriteplaces.FavoritePlaceDto;
import com.wanderfun.applicationlayer.dto.places.PlaceDto;
import com.wanderfun.applicationlayer.exception.ObjectNotFoundException;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.favoriteplaces.FavoritePlaceService;
import com.wanderfun.applicationlayer.service.place.PlaceService;
import com.wanderfun.applicationlayer.service.users.UserService;
import com.wanderfun.applicationlayer.usecase.FavoritePlaceUsecase;
import com.wanderfun.applicationlayer.util.JwtUtil;
import com.wanderfun.domainlayer.model.favoriteplaces.FavoritePlace;
import com.wanderfun.domainlayer.model.places.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritePlaceUsecaseImpl implements FavoritePlaceUsecase {
    private final FavoritePlaceService favoritePlaceService;
    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final PlaceService placeService;

    @Autowired
    public FavoritePlaceUsecaseImpl(FavoritePlaceService favoritePlaceService, ObjectMapper objectMapper, JwtUtil jwtUtil, UserService userService, PlaceService placeService) {
        this.favoritePlaceService = favoritePlaceService;
        this.objectMapper = objectMapper;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.placeService = placeService;
    }

    @Override
    public List<PlaceDto> findAllByUser(String accessToken) {
        Long userId = userService.findByAccountId(jwtUtil.getIdFromToken(accessToken)).getId();
        return objectMapper.mapList(favoritePlaceService.findAllByUserId(userId), PlaceDto.class);
    }

    @Override
    public FavoritePlaceDto create(String accessToken, Long placeId) {
        Long userId = userService.findByAccountId(jwtUtil.getIdFromToken(accessToken)).getId();

        try {
            FavoritePlace currentFavoritePlace = favoritePlaceService.findFavoritePlaceByUserIdAndPlaceId(userId, placeId);
            return objectMapper.map(currentFavoritePlace, FavoritePlaceDto.class);
        } catch (ObjectNotFoundException ignored) {
        }

        Place place = placeService.findById(placeId);
        FavoritePlace newFavoritePlace = new FavoritePlace();
        newFavoritePlace.setUserId(userId);
        newFavoritePlace.setPlace(place);

        FavoritePlace savedFavoritePlace = favoritePlaceService.create(newFavoritePlace);
        return objectMapper.map(savedFavoritePlace, FavoritePlaceDto.class);
    }

    @Override
    public boolean deleteById(String accessToken, Long placeId) {
        Long userId = userService.findByAccountId(jwtUtil.getIdFromToken(accessToken)).getId();
        try {
            FavoritePlace currentFavoritePlace = favoritePlaceService.findFavoritePlaceByUserIdAndPlaceId(userId, placeId);
            favoritePlaceService.deleteById(currentFavoritePlace.getId());
        } catch (ObjectNotFoundException ignored) {
        }
        return true;
    }
}
