package com.wanderfun.infrastructurelayer.service.places;

import com.wanderfun.applicationlayer.exception.ObjectNotFoundException;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.place.PlaceCategoryService;
import com.wanderfun.domainlayer.model.places.PlaceCategory;
import com.wanderfun.domainlayer.repository.place.PlaceCategoryRepository;
import com.wanderfun.infrastructurelayer.service.BaseServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaceCategoryServiceImpl extends BaseServiceImpl<PlaceCategory, Integer> implements PlaceCategoryService {
    private final PlaceCategoryRepository placeCategoryRepository;

    @Autowired
    public PlaceCategoryServiceImpl(PlaceCategoryRepository placeCategoryRepository, ObjectMapper objectMapper) {
        super(placeCategoryRepository, objectMapper, PlaceCategory.class);
        this.placeCategoryRepository = placeCategoryRepository;
    }

    @Override
    @Transactional
    public PlaceCategory findByName(String name) {
        return placeCategoryRepository.findByName(name)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("%s not found", PlaceCategory.class.getName())));
    }

    @Override
    @Transactional
    public PlaceCategory findByNameEn(String nameEn) {
        return placeCategoryRepository.findByNameEn(nameEn)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("%s not found", PlaceCategory.class.getName())));
    }

}
