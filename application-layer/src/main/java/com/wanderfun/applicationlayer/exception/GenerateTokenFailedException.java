package com.wanderfun.applicationlayer.exception;

public class GenerateTokenFailedException extends RuntimeException{
    public GenerateTokenFailedException(String message) {
        super(message);
    }
}
