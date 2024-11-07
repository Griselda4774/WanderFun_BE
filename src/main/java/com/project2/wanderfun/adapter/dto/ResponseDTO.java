package com.project2.wanderfun.adapter.dto;

public class ResponseDTO<T> {
    private String message;
    private T data;

    public ResponseDTO() {
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }
}
