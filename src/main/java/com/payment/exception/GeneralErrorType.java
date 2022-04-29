package com.payment.exception;

import org.springframework.http.HttpStatus;

public enum GeneralErrorType implements ApiErrorType {
    INTERNAL_SERVER_ERROR(1001, "Something went wrong.", HttpStatus.INTERNAL_SERVER_ERROR),
    CONSTRAINT_VALIDATION_ERROR(1003, "Constraint validations failed", HttpStatus.BAD_REQUEST);

    private static final String PREFIX = "PAY-";
    private HttpStatus httpStatusCode;
    private int code;
    private String message;

    GeneralErrorType(int code, String message, HttpStatus httpStatusCode) {
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
