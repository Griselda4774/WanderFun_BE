package com.project2.wanderfun.presentation.controller;

import com.project2.wanderfun.application.dto.place.PlaceCreateDto;
import com.project2.wanderfun.application.dto.place.PlaceDto;
import com.project2.wanderfun.application.dto.ResponseDto;
import com.project2.wanderfun.application.usecase.PlaceUsecase;
import com.project2.wanderfun.presentation.exception.RequestFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin
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

    @GetMapping("/search/{name}")
    public ResponseEntity<ResponseDto<List<PlaceDto>>> findAllPlacesByNameContaining(@PathVariable String name) {
        List<PlaceDto> result = placeUsecase.findAllPlacesByNameContaining(name);
        if (result == null) {
            throw new RequestFailedException("Find all places by name containing failed!");
        }

        ResponseDto<List<PlaceDto>> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find all places by name containing successful!");
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

    @GetMapping("/name/{name}")
    public ResponseEntity<ResponseDto<PlaceDto>> findPlaceByName(@PathVariable String name) {
        PlaceDto result = placeUsecase.findPlaceByName(name);
        if (result == null) {
            throw new RequestFailedException("Find place by name failed!");
        }

        ResponseDto<PlaceDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find place by name successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/coordinates")
    public ResponseEntity<ResponseDto<PlaceDto>> findPlaceByLongitudeAndLatitude(@RequestParam double longitude,
                                                                             @RequestParam double latitude) {
        PlaceDto result = placeUsecase.findPlaceByLongitudeAndLatitude(longitude, latitude);
        if (result == null) {
            throw new RequestFailedException("Find place by longitude and latitude failed!");
        }

        ResponseDto<PlaceDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find place by longitude and latitude successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping("")
    public ResponseEntity<ResponseDto<PlaceDto>> createPlace(@RequestBody PlaceCreateDto placeCreateDto) {
        boolean result = placeUsecase.createPlace(placeCreateDto);
        if (!result) {
            throw new RequestFailedException("Create place failed!");
        }

        ResponseDto<PlaceDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Create place successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<PlaceDto>> updatePlaceById(@PathVariable long id, @RequestBody PlaceCreateDto placeCreateDto) {
        boolean result = placeUsecase.updatePlaceById(id, placeCreateDto);
        if (!result) {
            throw new RequestFailedException("Update place failed!");
        }

        ResponseDto<PlaceDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Update place successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<PlaceDto>> deletePlaceById(@PathVariable long id) {
        boolean result = placeUsecase.deletePlaceById(id);
        if (!result) {
            throw new RequestFailedException("Delete place failed!");
        }

        ResponseDto<PlaceDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Delete place successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("")
    public ResponseEntity<ResponseDto<PlaceDto>> deleteAllPlaces() {
        boolean result = placeUsecase.deleteAllPlaces();
        if (!result) {
            throw new RequestFailedException("Delete all places failed!");
        }

        ResponseDto<PlaceDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Delete all places successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
