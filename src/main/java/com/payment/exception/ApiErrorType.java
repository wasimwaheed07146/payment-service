package com.payment.exception;

import org.springframework.http.HttpStatus;

public interface ApiErrorType {
    String PREFIX = "PAY-";

    HttpStatus getHttpStatusCode();

    int getCode();

    String getErrorCode();

    String getErrorMessage();
}
