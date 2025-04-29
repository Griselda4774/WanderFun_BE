package com.wanderfun.presentationlayer.controller;

import com.wanderfun.applicationlayer.dto.places.PlaceCategoryDto;
import com.wanderfun.applicationlayer.dto.ResponseDto;
import com.wanderfun.applicationlayer.usecase.places.PlaceCategoryUsecase;
import com.wanderfun.domainlayer.model.places.PlaceCategory;
import com.wanderfun.presentationlayer.exception.RequestFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("wanderfun/place")
public class PlaceCategoryController {
    private final PlaceCategoryUsecase placeCategoryUsecase;

    @Autowired
    public PlaceCategoryController(PlaceCategoryUsecase placeCategoryUsecase) {
        this.placeCategoryUsecase = placeCategoryUsecase;
    }

    @GetMapping("/categories")
    public ResponseEntity<ResponseDto<List<PlaceCategory>>> findAllPlaceCategory(@RequestHeader("Authorization") String accessToken){
        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }
        List<PlaceCategory> result = placeCategoryUsecase.findAllPlaceCategory();

        if(result == null) {
            throw new RequestFailedException("Find all favourite places failed!");
        }

        ResponseDto<List<PlaceCategory>> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find all place categories successfully!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/categories/{placeCategoryId}")
    public ResponseEntity<ResponseDto<PlaceCategory>> findPlaceCategoryById(@PathVariable Long placeCategoryId) {
        PlaceCategory result = placeCategoryUsecase.findPlaceCategoryById(placeCategoryId);
        if (result == null) {
            throw new RequestFailedException("Find place category failed!");
        }

        ResponseDto<PlaceCategory> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find place category successfully!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/categories")
    public ResponseEntity<ResponseDto<PlaceCategory>> addPlaceCategory(@RequestHeader("Authorization") String accessToken,
                                                                       @RequestBody PlaceCategoryDto placeCategoryDto){
        boolean result = placeCategoryUsecase.createPlaceCategory(placeCategoryDto);

        if (!result) {
            throw new RequestFailedException("Create place category failed!");
        }

        ResponseDto<PlaceCategory> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Create place category successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/categories/{placeCategoryId}")
    public ResponseEntity<ResponseDto<PlaceCategory>> updatePlaceCategory(@PathVariable Long placeCategoryId,
                                                                          @RequestBody PlaceCategoryDto placeCategoryDto) {
        boolean result = placeCategoryUsecase.updatePlaceCategoryById(placeCategoryId, placeCategoryDto);

        if (!result) {
            throw new RequestFailedException("Update place category failed!");
        }

        ResponseDto<PlaceCategory> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Update place category successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/categories/{placeCategoryId}")
    public ResponseEntity<ResponseDto<PlaceCategory>> deletePlaceCategory(@PathVariable Long placeCategoryId) {
        boolean result = placeCategoryUsecase.deletePlaceCategoryById(placeCategoryId);

        if (!result) {
            throw new RequestFailedException("Delete place category failed!");
        }

        ResponseDto<PlaceCategory> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Delete place category successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
