package com.payment.exception;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@RequiredArgsConstructor
public class PaymentException extends RuntimeException{
    private HttpStatus httpStatus;
    private String appCode;
    private String appMessage;

    @Builder
    public PaymentException(ApiErrorType apiErrorType) {
        super(apiErrorType.getErrorMessage());
        appMessage = apiErrorType.getErrorMessage();
        appCode = apiErrorType.getErrorCode();
        httpStatus = apiErrorType.getHttpStatusCode();
    }
}
