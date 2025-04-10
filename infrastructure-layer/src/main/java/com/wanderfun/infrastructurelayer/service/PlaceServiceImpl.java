package com.wanderfun.infrastructurelayer.service;

import com.wanderfun.applicationlayer.exception.ObjectNotFoundException;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.repository.PlaceRepository;
import com.wanderfun.domainlayer.repository.SectionRepository;
import com.wanderfun.applicationlayer.service.PlaceService;
import com.wanderfun.domainlayer.model.places.Place;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    @Transactional
    public List<Place> findByOrderByCheckInCountDesc() {
        return placeRepository.findByOrderByCheckInCountDesc();
    }
}
