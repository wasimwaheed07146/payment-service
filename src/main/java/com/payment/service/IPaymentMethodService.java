package com.payment.service;

import com.payment.dto.PaymentMethodDto;
import com.payment.dto.PaymentResponseDto;


/**
 * @author Wasim Waheed.
 * @since 1.0
 *
 * Service Interface for managing {@link com.payment.domain.PaymentMethodEntity}.
 */
public interface IPaymentMethodService {

    /**
     * Save a payment method and its plans.
     *
     * @param paymentMethodDto the entity to save.
     * @return the saved entity in the instance of {@link PaymentResponseDto}.
     */
    PaymentResponseDto save(PaymentMethodDto paymentMethodDto);

    /**
     * Get all payment methods
     *
     * @param paymentPlanId the {@link Integer} object for filtration.
     * @param paymentMethodName the {@link String} object for filtration.
     * @return the instance of {@link PaymentResponseDto}.
     */
    PaymentResponseDto findAll(Integer paymentPlanId, String paymentMethodName);

    /**
     * Update payment methods by id and its payment plans
     *
     * @param paymentMethodId instance {@link Integer}.
     * @param paymentMethodDto instance of {@link PaymentMethodDto}.
     * @return the instance of {@link PaymentResponseDto}.
     */
    PaymentResponseDto updatePaymentMethod(Integer paymentMethodId, PaymentMethodDto paymentMethodDto);
}
