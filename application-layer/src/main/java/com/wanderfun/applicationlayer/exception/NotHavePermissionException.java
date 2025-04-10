package com.wanderfun.applicationlayer.exception;

public class NotHavePermissionException extends RuntimeException {
    public NotHavePermissionException(String message) {
        super(message);
    }
}
