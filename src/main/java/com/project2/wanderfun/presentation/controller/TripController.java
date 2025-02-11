package com.project2.wanderfun.presentation.controller;

import com.project2.wanderfun.application.dto.ResponseDto;
import com.project2.wanderfun.application.dto.trip.TripCreateDto;
import com.project2.wanderfun.application.dto.trip.TripDto;
import com.project2.wanderfun.application.usecase.TripUsecase;

import com.project2.wanderfun.presentation.exception.RequestFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("wanderfun/trip")
public class TripController {
    private final TripUsecase tripUsecase;

    @Autowired
    public TripController(TripUsecase tripUsecase) {
        this.tripUsecase = tripUsecase;
    }

    @GetMapping("")
    public ResponseEntity<ResponseDto<List<TripDto>>> findAllTrips(@RequestHeader("Authorization") String accessToken) {
        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }
        List<TripDto> result = tripUsecase.findAllTrips(accessToken);
        if(result == null) {
            throw new RequestFailedException("Find all trips failed!");
        }

        ResponseDto<List<TripDto>> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find all trips successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<TripDto>> findTripById(@PathVariable long id) {
        TripDto result = tripUsecase.findTripById(id);
        if (result == null) {
            throw new RequestFailedException("Find trip failed!");
        }

        ResponseDto<TripDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find trip successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto<TripDto>> createTrip(@RequestHeader("Authorization") String accessToken,
                                                     @RequestBody TripCreateDto tripCreateDto) {
        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }

        boolean result = tripUsecase.createTrip(tripCreateDto, accessToken);
        if (!result) {
            throw new RequestFailedException("Create trip failed!");
        }

        ResponseDto<TripDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Create trip successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<TripDto>> updateTripById(@PathVariable long id,
                                                         @RequestBody TripCreateDto tripCreateDto) {
        boolean result = tripUsecase.updateTripById(id, tripCreateDto);
        if (!result) {
            throw new RequestFailedException("Update trip failed!");
        }

        ResponseDto<TripDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Update trip successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<TripDto>> deleteTripById(@PathVariable long id) {
        boolean result = tripUsecase.deleteTripById(id);
        if (!result) {
            throw new RequestFailedException("Delete trip failed!");
        }

        ResponseDto<TripDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Delete trip successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("")
    public ResponseEntity<ResponseDto<TripDto>> deleteAllTrips(@RequestHeader("Authorization") String accessToken) {
        boolean result = tripUsecase.deleteAllTrips(accessToken);
        if (!result) {
            throw new RequestFailedException("Delete all trips failed!");
        }

        ResponseDto<TripDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Delete all trips successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
