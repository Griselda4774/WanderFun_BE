package com.wanderfun.infrastructurelayer.usecase;

import com.wanderfun.applicationlayer.dto.addresses.ProvinceDto;
import com.wanderfun.applicationlayer.dto.places.MiniPlaceDto;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.addresses.ProvinceService;
import com.wanderfun.applicationlayer.service.place.PlaceService;
import com.wanderfun.applicationlayer.usecase.AutoCompleteSearchUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoCompleteSearchUsecaseImpl implements AutoCompleteSearchUsecase {
    private final ProvinceService provinceService;
    private final ObjectMapper objectMapper;
    private final PlaceService placeService;

    @Autowired
    public AutoCompleteSearchUsecaseImpl(ProvinceService provinceService, ObjectMapper objectMapper, PlaceService placeService) {
        this.provinceService = provinceService;
        this.objectMapper = objectMapper;
        this.placeService = placeService;
    }

    @Override
    public List<MiniPlaceDto> autoCompleteSearchPlace(String query) {
        return objectMapper.mapList(
                placeService.findAllByNameContaining(query).stream().limit(3).toList(),
                MiniPlaceDto.class
        );
    }

    @Override
    public List<ProvinceDto> autoCompleteSearchProvince(String query) {
        return objectMapper.mapList(
                provinceService.findAllByNameContaining(query).stream().limit(3).toList(),
                ProvinceDto.class
        );
    }
}
