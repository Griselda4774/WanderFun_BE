package com.project2.wanderfun.presentation.exception.handler;

import com.project2.wanderfun.application.dto.ResponseDto;
import com.project2.wanderfun.presentation.exception.InvalidRefreshTokenException;
import com.project2.wanderfun.presentation.exception.UserAlreadyExistException;
import com.project2.wanderfun.presentation.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidRefreshTokenException.class)
    public ResponseEntity<ResponseDto> handleInvalidRefreshTokenException(InvalidRefreshTokenException e) {
        ResponseDto response = new ResponseDto();
        response.setStatusCode(HttpStatus.UNAUTHORIZED.toString());
        response.setTimestamp(new Date());
        response.setMessage(e.getMessage());
        response.setData(null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ResponseDto> handleUserAlreadyExistException(UserAlreadyExistException e) {
        ResponseDto response = new ResponseDto();
        response.setStatusCode(HttpStatus.CONFLICT.toString());
        response.setTimestamp(new Date());
        response.setMessage(e.getMessage());
        response.setData(null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseDto> handleUserNotFoundException(UserNotFoundException e) {
        ResponseDto response = new ResponseDto();
        response.setStatusCode(HttpStatus.NOT_FOUND.toString());
        response.setTimestamp(new Date());
        response.setMessage(e.getMessage());
        response.setData(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> handleUnwantedException(Exception e) {
        ResponseDto response = new ResponseDto();
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        response.setTimestamp(new Date());
        response.setMessage("Something went wrong!");
        response.setData(null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
