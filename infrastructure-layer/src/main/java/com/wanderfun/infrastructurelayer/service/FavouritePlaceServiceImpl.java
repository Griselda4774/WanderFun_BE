package com.wanderfun.infrastructurelayer.service;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.repository.FavouritePlaceRepository;
import com.wanderfun.applicationlayer.service.FavouritePlaceService;
import com.wanderfun.domainlayer.model.users.FavouritePlace;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouritePlaceServiceImpl extends BaseServiceImpl<FavouritePlace> implements FavouritePlaceService {
    private final FavouritePlaceRepository favouritePlaceRepository;

    public FavouritePlaceServiceImpl(FavouritePlaceRepository favouritePlaceRepository, ObjectMapper objectMapper) {
        super(favouritePlaceRepository, objectMapper, FavouritePlace.class);
        this.favouritePlaceRepository = favouritePlaceRepository;
    }

    @Override
    @Transactional
    public List<FavouritePlace> findAllByUserId(Long userId) {
        List<FavouritePlace> favouritePlaces = favouritePlaceRepository.findAllByUser_Id(userId);
        return favouritePlaces;
    }

    @Override
    @Transactional
    public void deleteByIds(List<Long> ids) {
        favouritePlaceRepository.deleteByIds(ids);
    }
}