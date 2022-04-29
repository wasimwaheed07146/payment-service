package com.payment.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class ErrorResponse {
    private String errorCode;
    private String message;
    private String operationMessage;
    private HttpStatus httpStatus;
}
