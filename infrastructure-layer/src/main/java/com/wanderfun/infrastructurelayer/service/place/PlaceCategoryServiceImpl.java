package com.wanderfun.infrastructurelayer.service.place;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.place.PlaceCategoryService;
import com.wanderfun.domainlayer.model.places.PlaceCategory;
import com.wanderfun.domainlayer.repository.place.PlaceCategoryRepository;
import com.wanderfun.infrastructurelayer.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaceCategoryServiceImpl extends BaseServiceImpl<PlaceCategory> implements PlaceCategoryService {
    private final PlaceCategoryRepository placeCategoryRepository;

    @Autowired
    public PlaceCategoryServiceImpl(PlaceCategoryRepository placeCategoryRepository, ObjectMapper objectMapper) {
        super(placeCategoryRepository, objectMapper, PlaceCategory.class);
        this.placeCategoryRepository = placeCategoryRepository;
    }
}
