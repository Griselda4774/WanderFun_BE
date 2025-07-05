package com.wanderfun.presentationlayer.controller;

import com.wanderfun.applicationlayer.dto.ResponseDto;
import com.wanderfun.applicationlayer.dto.checkins.CheckInDto;
import com.wanderfun.applicationlayer.dto.places.MiniPlaceDto;
import com.wanderfun.applicationlayer.service.checkins.CheckInService;
import com.wanderfun.applicationlayer.usecase.CheckInUsecase;
import com.wanderfun.presentationlayer.exception.RequestFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/wanderfun/checkin")
public class CheckInController {
    private final CheckInUsecase checkInUsecase;

    @Autowired
    public CheckInController(CheckInUsecase checkInUsecase) {
        this.checkInUsecase = checkInUsecase;
    }

    @GetMapping("")
    public ResponseEntity<ResponseDto<List<CheckInDto>>> findAllByUser(@RequestHeader("Authorization") String accessToken) {
        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }

        List<CheckInDto> result = checkInUsecase.findAllByUser(accessToken);
        if (result == null) {
            throw new RequestFailedException("Find all check-ins by user failed!");
        }

        ResponseDto<List<CheckInDto>> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find all check-ins by user successful!");
        response.setData(result);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto<CheckInDto>> createCheckIn(@RequestHeader("Authorization") String accessToken,
                                                                 @RequestParam Long placeId) {
        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }

        CheckInDto result = checkInUsecase.create(accessToken, placeId);
        if (result == null) {
            throw new RequestFailedException("Create check-in failed!");
        }

        ResponseDto<CheckInDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.CREATED.toString());
        response.setMessage("Create check-in successful!");
        response.setData(result);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/eligible-places")
    public ResponseEntity<ResponseDto<List<MiniPlaceDto>>> findAllEligiblePlaces(
            @RequestParam double userLng,
            @RequestParam double userLat) {

        List<MiniPlaceDto> result = checkInUsecase.findAllEligiblePlaces(userLng, userLat);
        if (result == null) {
            throw new RequestFailedException("Find all eligible places failed!");
        }

        ResponseDto<List<MiniPlaceDto>> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find all eligible places successful!");
        response.setData(result);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
