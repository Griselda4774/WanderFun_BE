package com.project2.wanderfun.infrastructure.service;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.repository.FavouritePlaceRepository;
import com.project2.wanderfun.application.service.FavouritePlaceService;
import com.project2.wanderfun.domain.model.FavouritePlace;
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