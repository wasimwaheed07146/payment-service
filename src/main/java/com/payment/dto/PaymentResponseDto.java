package com.payment.dto;

import lombok.*;

import java.util.List;

/**
 * @author Wasim Waheed.
 * @since 1.0
 */
@Builder
@Data
public class PaymentResponseDto {
    private List<PaymentMethodDto> paymentMethods;
}
