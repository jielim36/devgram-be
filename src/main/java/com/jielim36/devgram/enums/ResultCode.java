package com.jielim36.devgram.enums;

public enum  ResultCode {
    SUCCESS(200, "Success"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    METHOD_NOT_ALLOWED(405,"Method Not Allowed");

    private Integer code;
    private String message;
    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getResultCode() {
        return this.code.toString();
    }

    public String getResultMsg() {
        return this.message;
    }
}