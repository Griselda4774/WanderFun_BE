package com.project2.wanderfun.presentation.exception.handler;

import com.project2.wanderfun.application.dto.ResponseDto;
import com.project2.wanderfun.application.exception.*;
import com.project2.wanderfun.presentation.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotHavePermissionException.class)
    public ResponseEntity<ResponseDto<?>> handleNotHavePermissionException(NotHavePermissionException e) {
        ResponseDto<?> response = new ResponseDto();
        response.setStatusCode(HttpStatus.FORBIDDEN.toString());
        response.setError(true);
        response.setErrorType(NotHavePermissionException.class.getSimpleName());
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ResponseDto<?>> handleUnauthorizedException(UnauthorizedException e) {
        ResponseDto<?> response = new ResponseDto();
        response.setStatusCode(HttpStatus.UNAUTHORIZED.toString());
        response.setError(true);
        response.setErrorType(UnauthorizedException.class.getSimpleName());
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ExceptionHandler(WrongEmailOrPasswordException.class)
    public ResponseEntity<ResponseDto<?>> handleWrongEmailOrPasswordException(WrongEmailOrPasswordException e) {
        ResponseDto<?> response = new ResponseDto();
        response.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        response.setError(true);
        response.setErrorType(WrongEmailOrPasswordException.class.getSimpleName());
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ResponseDto<?>> handleObjectNotFoundException(ObjectNotFoundException e) {
        ResponseDto<?> response = new ResponseDto();
        response.setStatusCode(HttpStatus.NOT_FOUND.toString());
        response.setError(true);
        response.setErrorType(ObjectNotFoundException.class.getSimpleName());
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ExceptionHandler(ObjectAlreadyExistException.class)
    public ResponseEntity<ResponseDto<?>> handleObjectAlreadyExistException(ObjectAlreadyExistException e) {
        ResponseDto<?> response = new ResponseDto();
        response.setStatusCode(HttpStatus.CONFLICT.toString());
        response.setError(true);
        response.setErrorType(ObjectAlreadyExistException.class.getSimpleName());
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ExceptionHandler(ObjectInvalidException.class)
    public ResponseEntity<ResponseDto<?>> handleObjectInvalidException(ObjectInvalidException e) {
        ResponseDto<?> response = new ResponseDto();
        response.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        response.setError(true);
        response.setErrorType(ObjectInvalidException.class.getSimpleName());
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ExceptionHandler(GenerateTokenFailedException.class)
    public ResponseEntity<ResponseDto<?>> handleGenerateTokenFailedException(GenerateTokenFailedException e) {
        ResponseDto<?> response = new ResponseDto();
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        response.setError(true);
        response.setErrorType(GenerateTokenFailedException.class.getSimpleName());
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ExceptionHandler(GenerateCloudinarySignatureFailedException.class)
    public ResponseEntity<ResponseDto<?>> handleGenerateCloudinarySignatureFailedException(GenerateCloudinarySignatureFailedException e) {
        ResponseDto<?> response = new ResponseDto();
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        response.setError(true);
        response.setErrorType(GenerateCloudinarySignatureFailedException.class.getSimpleName());
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ExceptionHandler(CloudinaryDeleteImageFailedException.class)
    public ResponseEntity<ResponseDto<?>> handleCloudinaryDeleteImageFailedException(CloudinaryDeleteImageFailedException e) {
        ResponseDto<?> response = new ResponseDto();
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        response.setError(true);
        response.setErrorType(CloudinaryDeleteImageFailedException.class.getSimpleName());
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ExceptionHandler(RequestFailedException.class)
    public ResponseEntity<ResponseDto<?>> handleRequestFailedException(RequestFailedException e) {
        ResponseDto<?> response = new ResponseDto();
        response.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        response.setError(true);
        response.setErrorType(RequestFailedException.class.getSimpleName());
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<?>> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        ResponseDto<?> response = new ResponseDto();
        response.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        response.setError(true);
        response.setErrorType(MethodArgumentNotValidException.class.getSimpleName());
        response.setMessage("Information is invalid!");
        response.setErrorData(errors);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseDto<?>> handleMethodArgumentTypeMismatchException (MethodArgumentTypeMismatchException e) {
        ResponseDto<Map> response = new ResponseDto();
        response.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        response.setError(true);
        response.setErrorType(MethodArgumentTypeMismatchException.class.getSimpleName());
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<?>> handleUnwantedException(Exception e) {
        ResponseDto<?> response = new ResponseDto();
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        response.setError(true);
        response.setErrorType(Exception.class.getSimpleName());
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
