package com.payment.exception;

import org.springframework.http.HttpStatus;

public enum ApplicationErrorType implements ApiErrorType{
    PAYMENT_METHOD_NOT_FOUND(4001, "Payment method not found", HttpStatus.NOT_FOUND),
    PAYMENT_METHOD_NAME_ALREADY_EXISTS(4002, "Payment method name already exists", HttpStatus.BAD_REQUEST);
    private static final String PREFIX = "PAY-";
    private HttpStatus httpStatusCode;
    private int code;
    private String message;

    ApplicationErrorType(int code, String message, HttpStatus httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    @Override
    public String getErrorCode() {
        return PREFIX + this.code;
    }

    @Override
    public String getErrorMessage() {
        return this.message;
    }

    @Override
    public HttpStatus getHttpStatusCode() {
        return this.httpStatusCode;
    }

    @Override
    public int getCode() {
        return this.code;
    }
}
