package com.project2.wanderfun.infrastructure.service;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.repository.PlaceRepository;
import com.project2.wanderfun.application.service.PlaceService;
import com.project2.wanderfun.domain.model.Place;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceServiceImpl extends BaseServiceImpl<Place> implements PlaceService {
    private final PlaceRepository placeRepository;

    @Autowired
    public PlaceServiceImpl(PlaceRepository placeRepository, ObjectMapper objectMapper) {
        super(placeRepository, objectMapper, Place.class);
        this.placeRepository = placeRepository;
    }

    @Override
    @Transactional
    public List<Place> findByNameContaining(String name) {
        return placeRepository.findByNameContaining(name);
    }
}
