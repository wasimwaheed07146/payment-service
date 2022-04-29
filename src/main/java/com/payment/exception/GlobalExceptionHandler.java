package com.payment.exception;

import com.payment.dto.PaymentMethodDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * This method will handle all Runtime Exceptions generated in application
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        log.error(ex.getMessage(), ex);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(GeneralErrorType.INTERNAL_SERVER_ERROR.getErrorCode())
                .message(GeneralErrorType.INTERNAL_SERVER_ERROR.getErrorMessage())
                .operationMessage(ex.getMessage())
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }


    /**
     * This method will handle all Exceptions if it is an instance of {@link PaymentException}
     */
    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<ErrorResponse> handlePaymentServiceException(PaymentException ex) {
        log.error(ex.getMessage(), ex);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(ex.getAppCode())
                .message(ex.getAppMessage())
                .operationMessage(ex.getMessage())
                .httpStatus(ex.getHttpStatus())
                .build();
        return ResponseEntity.status(ex.getHttpStatus()).body(errorResponse);
    }


    /**
     * This method will handle all invalid argument exceptions
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage(), ex);
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> "[" + e.getDefaultMessage() + "]")
                .collect(Collectors.joining(","));

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(GeneralErrorType.CONSTRAINT_VALIDATION_ERROR.getErrorCode())
                .message(errors)
                .httpStatus(GeneralErrorType.CONSTRAINT_VALIDATION_ERROR.getHttpStatusCode())
                .build();
        return ResponseEntity.status(GeneralErrorType.CONSTRAINT_VALIDATION_ERROR.getHttpStatusCode())
                .body(errorResponse);
    }
}
