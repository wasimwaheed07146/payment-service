package com.payment.service.impl;

import com.payment.domain.PaymentMethodEntity;
import com.payment.dto.PaymentFilter;
import com.payment.dto.PaymentMethodDto;
import com.payment.dto.PaymentResponseDto;
import com.payment.exception.ApplicationErrorType;
import com.payment.exception.PaymentException;
import com.payment.mappers.PaymentMethodMapper;
import com.payment.repository.PaymentMethodRepository;
import com.payment.repository.PaymentPlanRepository;
import com.payment.service.IPaymentMethodService;
import com.payment.specs.PaymentMethodSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
/**
 * @author Wasim Waheed.
 * @since 1.0
 *
 * Service Implementation for managing {@link PaymentMethodEntity}.
 */
@Service
@Transactional
@Slf4j
public class PaymentMethodService implements IPaymentMethodService {
    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentPlanRepository paymentPlanRepository;
    private final PaymentMethodMapper paymentMethodMapper;

    @Autowired
    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository, PaymentPlanRepository paymentPlanRepository, PaymentMethodMapper paymentMethodMapper){
        this.paymentMethodRepository = paymentMethodRepository;
        this.paymentPlanRepository = paymentPlanRepository;
        this.paymentMethodMapper = paymentMethodMapper;
    }
    /**
     * Saves a new payment method along with multiple payment plan.
     * @param paymentMethodDto An instance of {@link PaymentMethodDto}
     */
    @Override
    public PaymentResponseDto save(PaymentMethodDto paymentMethodDto) {
        log.debug("Inside save method of PaymentMethodService to save Payment method : {}", paymentMethodDto);
        if (paymentMethodRepository.existsByName(paymentMethodDto.getName())){
                throw PaymentException.builder()
                        .apiErrorType(ApplicationErrorType.PAYMENT_METHOD_NAME_ALREADY_EXISTS)
                        .build();
        }
        PaymentMethodEntity paymentMethodEntity = paymentMethodMapper.mapToEntity(paymentMethodDto);
        paymentMethodEntity.getPaymentPlans()
               .forEach(paymentPlan -> paymentPlan.setPaymentMethodEntity(paymentMethodEntity));

        paymentMethodDto = paymentMethodMapper.mapToDto(paymentMethodRepository.save(paymentMethodEntity));
        return PaymentResponseDto.builder()
                .paymentMethods(Arrays.asList(paymentMethodDto))
                .build();
    }

    /**
     * Get all payment methods along with their payment plans based on the given fields. If filtration fields are empty
     *  then all payment methods are returned.
     * paymentPlanId instance of {@link Integer} or
     * paymentMethodName instance of {@link String PaymentPlan}
     */
    @Override
    public PaymentResponseDto findAll(Integer paymentPlanId, String paymentMethodName) {
        log.debug("Inside findAll method of PaymentMethodService to get all Payment method with paymentPlanId: {} and paymentMethodName: {}", paymentPlanId,paymentMethodName);

       List<PaymentMethodEntity> list =  paymentMethodRepository.findAll(new PaymentMethodSpecification(PaymentFilter.builder()
               .paymentPlanId(paymentPlanId)
               .paymentMethodName(paymentMethodName)
               .build()));

       return PaymentResponseDto.builder()
               .paymentMethods(paymentMethodMapper.mapToDto(list))
               .build();
    }

    /**
     * Updates an existing payment method by given paymentMethodId.
     *
     * @param paymentMethodId instance of {@link Integer}.It is Primary key of the payment method to update
     * @param request instance of {@link PaymentMethodDto} containing details for the payment method to be
     *                      updated.
     */
    @Override
    public PaymentResponseDto updatePaymentMethod(Integer paymentMethodId, PaymentMethodDto request) {
        log.debug("Inside updatePaymentMethod method of PaymentMethodService update Payment method with paymentPlanId: {} and request: {}", paymentMethodId,request);

        PaymentMethodEntity existingPaymentMethodEntity = paymentMethodRepository.findById(paymentMethodId)
                .orElseThrow(() -> PaymentException
                .builder()
                .apiErrorType(ApplicationErrorType.PAYMENT_METHOD_NOT_FOUND)
                .build());

        PaymentMethodEntity entity =  paymentMethodMapper.updatePaymentMethod(existingPaymentMethodEntity,request);
        entity.getPaymentPlans()
                .forEach(paymentPlan -> paymentPlan.setPaymentMethodEntity(entity));
        PaymentMethodDto updatedDto = paymentMethodMapper.mapToDto(paymentMethodRepository.save(entity));

        return PaymentResponseDto.builder()
                .paymentMethods(Arrays.asList(updatedDto))
                .build();

    }
}
