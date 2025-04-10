package com.wanderfun.applicationlayer.exception;

public class ObjectAlreadyExistException extends RuntimeException{
    public ObjectAlreadyExistException(String message) {
        super(message);
    }
}
