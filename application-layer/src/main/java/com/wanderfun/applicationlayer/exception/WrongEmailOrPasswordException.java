package com.wanderfun.applicationlayer.exception;

public class WrongEmailOrPasswordException extends RuntimeException {
    public WrongEmailOrPasswordException(String message) {
        super(message);
    }
}
