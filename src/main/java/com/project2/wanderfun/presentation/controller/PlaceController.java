package com.project2.wanderfun.presentation.controller;

import com.project2.wanderfun.application.dto.PlaceDto;
import com.project2.wanderfun.application.dto.ResponseDto;
import com.project2.wanderfun.application.usecase.PlaceUsecase;
import com.project2.wanderfun.presentation.exception.RequestFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("wanderfun/place")
public class PlaceController {
    private final PlaceUsecase placeUsecase;

    @Autowired
    public PlaceController(PlaceUsecase placeUsecase) {
        this.placeUsecase = placeUsecase;
    }

    @GetMapping("")
    public ResponseEntity<ResponseDto<List<PlaceDto>>> findAllPlaces() {
        List<PlaceDto> result = placeUsecase.findAllPlaces();
        if(result == null) {
            throw new RequestFailedException("Find all places failed!");
        }

        ResponseDto<List<PlaceDto>> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find all places successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<PlaceDto>> findPlaceById(@PathVariable long id) {
        PlaceDto result = placeUsecase.findPlaceById(id);
        if (result == null) {
            throw new RequestFailedException("Find place failed!");
        }

        ResponseDto<PlaceDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find place successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto<?>> createPlace(@RequestBody PlaceDto placeDto) {
        boolean result = placeUsecase.createPlace(placeDto);
        if (!result) {
            throw new RequestFailedException("Create place failed!");
        }

        ResponseDto<?> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Create place successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<?>> updatePlaceById(@PathVariable long id, @RequestBody PlaceDto placeDto) {
        boolean result = placeUsecase.updatePlaceById(id, placeDto);
        if (!result) {
            throw new RequestFailedException("Update place failed!");
        }

        ResponseDto<?> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Update place successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<?>> deletePlaceById(@PathVariable long id) {
        boolean result = placeUsecase.deletePlaceById(id);
        if (!result) {
            throw new RequestFailedException("Delete place failed!");
        }

        ResponseDto<?> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Delete place successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("")
    public ResponseEntity<ResponseDto<?>> deleteAllPlaces() {
        boolean result = placeUsecase.deleteAllPlaces();
        if (!result) {
            throw new RequestFailedException("Delete all places failed!");
        }

        ResponseDto<?> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Delete all places successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
