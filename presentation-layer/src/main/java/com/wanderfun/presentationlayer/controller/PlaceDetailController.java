package com.wanderfun.presentationlayer.controller;

import com.wanderfun.applicationlayer.usecase.places.PlaceDetailUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("wanderfun/place/details")
public class PlaceDetailController {
    private final PlaceDetailUsecase placeDetailUsecase;

    @Autowired
    public PlaceDetailController(PlaceDetailUsecase placeDetailUsecase) {
        this.placeDetailUsecase = placeDetailUsecase;
    }
}
