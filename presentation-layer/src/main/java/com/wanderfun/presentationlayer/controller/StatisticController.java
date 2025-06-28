package com.wanderfun.presentationlayer.controller;

import com.wanderfun.applicationlayer.dto.ResponseDto;
import com.wanderfun.applicationlayer.dto.places.PlaceDto;
import com.wanderfun.applicationlayer.dto.statistics.PlaceRankingDto;
import com.wanderfun.applicationlayer.dto.statistics.StatisticDto;
import com.wanderfun.applicationlayer.dto.statistics.UserRankingDto;
import com.wanderfun.applicationlayer.usecase.StatisticUsecase;
import com.wanderfun.presentationlayer.exception.RequestFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("wanderfun/statistics")
public class StatisticController {
    private final StatisticUsecase statisticUsecase;

    @Autowired
    public StatisticController(StatisticUsecase statisticUsecase) {
        this.statisticUsecase = statisticUsecase;
    }

    @GetMapping("/place-ranking")
    public ResponseEntity<ResponseDto<List<PlaceRankingDto>>> getPlaceRanking() {
        List<PlaceRankingDto> result = statisticUsecase.getPlaceRanking();
        if(result == null) {
            throw new RequestFailedException("Find place ranking failed!");
        }

        ResponseDto<List<PlaceRankingDto>> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find place ranking successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/user-ranking")
    public ResponseEntity<ResponseDto<List<UserRankingDto>>> getUserRanking() {
        List<UserRankingDto> result = statisticUsecase.getUserRanking();
        if(result == null) {
            throw new RequestFailedException("Find user ranking failed!");
        }

        ResponseDto<List<UserRankingDto>> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find user ranking successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDto<StatisticDto>> getStatistics() {
        StatisticDto result = statisticUsecase.getStatistics();
        if(result == null) {
            throw new RequestFailedException("Find statistics failed!");
        }

        ResponseDto<StatisticDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find statistics successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
