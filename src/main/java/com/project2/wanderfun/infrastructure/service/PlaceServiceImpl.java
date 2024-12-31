package com.project2.wanderfun.infrastructure.service;

import com.project2.wanderfun.application.exception.ObjectNotFoundException;
import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.repository.PlaceRepository;
import com.project2.wanderfun.application.repository.SectionRepository;
import com.project2.wanderfun.application.service.PlaceService;
import com.project2.wanderfun.domain.model.Place;
import com.project2.wanderfun.domain.model.Section;
import com.project2.wanderfun.infrastructure.persistence.entity.SectionEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PlaceServiceImpl extends BaseServiceImpl<Place> implements PlaceService {
    private final PlaceRepository placeRepository;
    private final SectionRepository sectionRepository;

    @Autowired
    public PlaceServiceImpl(PlaceRepository placeRepository, SectionRepository sectionRepository, ObjectMapper objectMapper) {
        super(placeRepository, objectMapper, Place.class);
        this.placeRepository = placeRepository;
        this.sectionRepository = sectionRepository;
    }

    @Override
    @Transactional
    public List<Place> findAllByNameContaining(String name) {
        return placeRepository.findAllByNameContaining(name);
    }

    @Override
    @Transactional
    public Place findByName(String name) {
        return placeRepository.findByName(name)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("%s not found", Place.class.getSimpleName())));
    }

    @Override
    @Transactional
    public Place findByLongitudeAndLatitude(double longitude, double latitude) {
        return placeRepository.findByLongitudeAndLatitude(longitude, latitude)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("%s not found", Place.class.getSimpleName())));
    }
}
