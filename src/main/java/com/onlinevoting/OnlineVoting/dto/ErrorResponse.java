package com.onlinevoting.OnlineVoting.dto;

public class ErrorResponse {
    private int code;
    private String message;


    public ErrorResponse() {
    }

    public ErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int status) {
        this.code = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
