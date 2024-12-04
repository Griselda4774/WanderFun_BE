package com.project2.wanderfun.application.exception;

public class WrongEmailOrPasswordException extends RuntimeException {
    public WrongEmailOrPasswordException(String message) {
        super(message);
    }
}
