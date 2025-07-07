package com.wanderfun.presentationlayer.controller;

import com.wanderfun.applicationlayer.dto.ResponseDto;

import com.wanderfun.applicationlayer.dto.favoriteplaces.FavoritePlaceDto;
import com.wanderfun.applicationlayer.dto.places.PlaceDto;
import com.wanderfun.applicationlayer.usecase.FavoritePlaceUsecase;
import com.wanderfun.presentationlayer.exception.RequestFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/wanderfun/favorite-place")
public class FavoritePlaceController {
    private final FavoritePlaceUsecase favoritePlaceUsecase;

    @Autowired
    public FavoritePlaceController(FavoritePlaceUsecase favoritePlaceUsecase) {
        this.favoritePlaceUsecase = favoritePlaceUsecase;
    }

    @GetMapping("")
    public ResponseEntity<ResponseDto<List<PlaceDto>>> findAllByUser(@RequestHeader("Authorization") String accessToken) {
        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }

        List<PlaceDto> result = favoritePlaceUsecase.findAllByUser(accessToken);
        if (result == null) {
            throw new RequestFailedException("Find all favorite place by user failed!");
        }

        ResponseDto<List<PlaceDto>> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find all favorite place by user successful!");
        response.setData(result);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto<PlaceDto>> createFavoritePlace(@RequestHeader("Authorization") String accessToken,
                                                                             @RequestParam Long placeId) {
        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }

        PlaceDto result = favoritePlaceUsecase.create(accessToken, placeId);
        if (result == null) {
            throw new RequestFailedException("Create check-in failed!");
        }

        ResponseDto<PlaceDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.CREATED.toString());
        response.setMessage("Create check-in successful!");
        response.setData(result);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("")
    public ResponseEntity<ResponseDto<PlaceDto>> deleteByUserAndPlaceId(@RequestHeader("Authorization") String accessToken,
                                                                                @RequestParam Long placeId) {
        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }

        PlaceDto result = favoritePlaceUsecase.deleteById(accessToken, placeId);
        if (result == null) {
            throw new RequestFailedException("Delete favorite place failed!");
        }

        ResponseDto<PlaceDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Delete favorite place successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
