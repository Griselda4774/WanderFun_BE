package com.wanderfun.presentationlayer.controller.places;

import com.wanderfun.applicationlayer.dto.ResponseDto;
import com.wanderfun.applicationlayer.dto.places.FeedbackCreateDto;
import com.wanderfun.applicationlayer.dto.places.FeedbackDto;
import com.wanderfun.applicationlayer.usecase.places.FeedbackUsecase;
import com.wanderfun.presentationlayer.exception.RequestFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("wanderfun/feedback")
public class FeedbackController {
    private final FeedbackUsecase feedbackUsecase;

    @Autowired
    public FeedbackController(FeedbackUsecase feedbackUsecase) {
        this.feedbackUsecase = feedbackUsecase;
    }

    // Feedback-related endpoints
    @GetMapping("")
    public ResponseEntity<ResponseDto<List<FeedbackDto>>> findAllFeedbacksByPlaceId(@RequestParam Long placeId) {
        List<FeedbackDto> result = feedbackUsecase.findAllByPlaceId(placeId);
        if (result == null) {
            throw new RequestFailedException("Find all feedbacks by place id failed!");
        }

        ResponseDto<List<FeedbackDto>> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find all feedback by place id successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping("")
    public ResponseEntity<ResponseDto<FeedbackDto>> createFeedback(@RequestHeader("Authorization") String accessToken,
                                                                   @RequestParam Long placeId,
                                                                   @RequestBody FeedbackCreateDto feedbackCreateDto) {
        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }

        FeedbackDto result = feedbackUsecase.create(accessToken, placeId, feedbackCreateDto);
        if (result == null) {
            throw new RequestFailedException("Create feedback failed!");
        }

        ResponseDto<FeedbackDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.CREATED.toString());
        response.setMessage("Create feedback successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{feedbackId}")
    public ResponseEntity<ResponseDto<FeedbackDto>> updateFeedback(@PathVariable Long feedbackId,
                                                                   @RequestHeader("Authorization") String accessToken,
                                                                   @RequestBody FeedbackCreateDto feedbackCreateDto) {
        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }

        FeedbackDto result = feedbackUsecase.updateById(accessToken, feedbackId, feedbackCreateDto);
        if (result == null) {
            throw new RequestFailedException("Update feedback failed!");
        }

        ResponseDto<FeedbackDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Update feedback successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{feedbackId}")
    public ResponseEntity<ResponseDto<FeedbackDto>> deleteFeedback(@PathVariable Long feedbackId,
                                                                   @RequestHeader("Authorization") String accessToken) {
        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }

        boolean result = feedbackUsecase.deleteById(accessToken, feedbackId);
        if (!result) {
            throw new RequestFailedException("Delete feedback failed!");
        }

        ResponseDto<FeedbackDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Delete feedback successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
