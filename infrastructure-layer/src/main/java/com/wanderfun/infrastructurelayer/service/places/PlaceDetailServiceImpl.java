package com.wanderfun.infrastructurelayer.service.places;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.place.PlaceDetailService;
import com.wanderfun.domainlayer.model.places.PlaceDetail;
import com.wanderfun.domainlayer.repository.place.PlaceDetailRepository;
import com.wanderfun.infrastructurelayer.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaceDetailServiceImpl extends BaseServiceImpl<PlaceDetail, Long> implements PlaceDetailService {
    private final PlaceDetailRepository placeDetailRepository;

    @Autowired
    public PlaceDetailServiceImpl(PlaceDetailRepository placeDetailRepository, ObjectMapper objectMapper) {
        super(placeDetailRepository, objectMapper, PlaceDetail.class);
        this.placeDetailRepository = placeDetailRepository;
    }
}
