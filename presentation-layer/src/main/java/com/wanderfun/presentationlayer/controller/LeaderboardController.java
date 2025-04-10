package com.wanderfun.presentationlayer.controller;

import com.wanderfun.applicationlayer.dto.ResponseDto;
import com.wanderfun.applicationlayer.dto.leaderboard.LeaderboardPlaceDto;
import com.wanderfun.applicationlayer.dto.leaderboard.LeaderboardUserDto;
import com.wanderfun.applicationlayer.usecase.LeaderboardUsecase;
import com.wanderfun.presentationlayer.exception.RequestFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("wanderfun/leaderboard")
public class LeaderboardController {
    private final LeaderboardUsecase leaderboardUsecase;

    @Autowired
    public LeaderboardController(LeaderboardUsecase leaderboardUsecase) {
        this.leaderboardUsecase = leaderboardUsecase;
    }

    @GetMapping("/user")
    public ResponseEntity<ResponseDto<List<LeaderboardUserDto>>> getLeaderboardUser() {
        List<LeaderboardUserDto> result = leaderboardUsecase.getUserLeaderboard();
        if(result == null) {
            throw new RequestFailedException("Get user leaderboard failed!");
        }

        ResponseDto<List<LeaderboardUserDto>> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Get user leaderboard successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/place")
    public ResponseEntity<ResponseDto<List<LeaderboardPlaceDto>>> getLeaderboardPlace() {
        List<LeaderboardPlaceDto> result = leaderboardUsecase.getPlaceLeaderboard();
        if(result == null) {
            throw new RequestFailedException("Get place leaderboard failed!");
        }

        ResponseDto<List<LeaderboardPlaceDto>> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Get place leaderboard successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/user")
    public ResponseEntity<ResponseDto<LeaderboardUserDto>> updateLeaderboardUser() {
        boolean result = leaderboardUsecase.updateLeaderboardUser();
        if (!result) {
            throw new RequestFailedException("Update leaderboard user failed!");
        }

        ResponseDto<LeaderboardUserDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Update leaderboard user successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/place")
    public ResponseEntity<ResponseDto<LeaderboardPlaceDto>> updateLeaderboardPlace() {
        boolean result = leaderboardUsecase.updateLeaderboardPlace();
        if (!result) {
            throw new RequestFailedException("Update leaderboard place failed!");
        }

        ResponseDto<LeaderboardPlaceDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Update leaderboard place successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
