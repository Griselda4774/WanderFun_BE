package com.project2.wanderfun.presentation.exception.handler;

import com.project2.wanderfun.application.dto.ResponseDto;
import com.project2.wanderfun.presentation.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ResponseDto<?>> handleUnauthorizedException(UnauthorizedException e) {
        ResponseDto<?> response = new ResponseDto();
        response.setStatusCode(HttpStatus.UNAUTHORIZED.toString());
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(WrongEmailOrPasswordException.class)
    public ResponseEntity<ResponseDto<?>> handleWrongEmailOrPasswordException(WrongEmailOrPasswordException e) {
        ResponseDto<?> response = new ResponseDto();
        response.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ResponseDto<?>> handleObjectNotFoundException(ObjectNotFoundException e) {
        ResponseDto<?> response = new ResponseDto();
        response.setStatusCode(HttpStatus.NOT_FOUND.toString());
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ObjectAlreadyExistException.class)
    public ResponseEntity<ResponseDto<?>> handleObjectAlreadyExistException(ObjectAlreadyExistException e) {
        ResponseDto<?> response = new ResponseDto();
        response.setStatusCode(HttpStatus.CONFLICT.toString());
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(ObjectInvalidException.class)
    public ResponseEntity<ResponseDto<?>> handleObjectInvalidException(ObjectInvalidException e) {
        ResponseDto<?> response = new ResponseDto();
        response.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(RequestFailedException.class)
    public ResponseEntity<ResponseDto<?>> handleRequestFailedException(RequestFailedException e) {
        ResponseDto<?> response = new ResponseDto();
        response.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<Map>> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        ResponseDto<Map> response = new ResponseDto();
        response.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        response.setMessage("Information is invalid!");
        response.setData(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseDto<?>> handleMethodArgumentTypeMismatchException (MethodArgumentTypeMismatchException e) {
        ResponseDto<Map> response = new ResponseDto();
        response.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        response.setMessage("Can not convert data type!");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<?>> handleUnwantedException(Exception e) {
        ResponseDto<?> response = new ResponseDto();
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        response.setMessage("Something went wrong!");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
