package com.project2.wanderfun.application.dto;

import java.util.Date;

public class ResponseDto<T> {
    private String statusCode;
    private boolean isError;
    private String errorType;
    private Date timestamp;
    private String message;
    private T data;

    public ResponseDto() {
        this.timestamp = new Date();
        this.data = null;
        this.isError = false;
        this.errorType = null;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
