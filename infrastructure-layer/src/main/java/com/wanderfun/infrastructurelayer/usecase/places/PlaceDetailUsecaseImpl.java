package com.wanderfun.infrastructurelayer.usecase.places;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.place.PlaceDetailService;
import com.wanderfun.applicationlayer.usecase.places.PlaceDetailUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaceDetailUsecaseImpl implements PlaceDetailUsecase {
    private final PlaceDetailService placeDetailService;
    private final ObjectMapper objectMapper;

    @Autowired
    public PlaceDetailUsecaseImpl(PlaceDetailService placeDetailService, ObjectMapper objectMapper) {
        this.placeDetailService = placeDetailService;
        this.objectMapper = objectMapper;
    }
}
