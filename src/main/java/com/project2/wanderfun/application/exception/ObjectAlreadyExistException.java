package com.project2.wanderfun.application.exception;

public class ObjectAlreadyExistException extends RuntimeException{
    public ObjectAlreadyExistException(String message) {
        super(message);
    }
}
