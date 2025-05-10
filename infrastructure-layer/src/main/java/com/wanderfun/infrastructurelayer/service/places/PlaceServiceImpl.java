package com.wanderfun.infrastructurelayer.service.places;

import com.wanderfun.applicationlayer.exception.ObjectNotFoundException;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.repository.place.PlaceRepository;
import com.wanderfun.applicationlayer.service.place.PlaceService;
import com.wanderfun.domainlayer.model.places.Place;
import com.wanderfun.infrastructurelayer.service.BaseServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceServiceImpl extends BaseServiceImpl<Place, Long> implements PlaceService {
    private final PlaceRepository placeRepository;

    @Autowired
    public PlaceServiceImpl(PlaceRepository placeRepository, ObjectMapper objectMapper) {
        super(placeRepository, objectMapper, Place.class);
        this.placeRepository = placeRepository;
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
    public List<Place> findAllByProvinceName(String provinceName) {
        return placeRepository.findAllByProvinceName(provinceName);
    }

    @Override
    @Transactional
    public List<Place> findAllByCategoryId(Long categoryId) {
        return placeRepository.findAllByCategoryId(categoryId);
    }
}
