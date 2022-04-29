package com.payment.controller;

import com.payment.domain.PaymentMethodEntity;
import com.payment.dto.PaymentMethodDto;
import com.payment.dto.PaymentResponseDto;
import com.payment.service.IPaymentMethodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * REST controller for managing {@link com.payment.domain.PaymentMethodEntity}.
 */
@Tag(name = "Payment Method")
@Slf4j
@RestController
@RequestMapping("api/v1.0/configuration/payment-methods")
public class PaymentMethodController {


    private final IPaymentMethodService service;

    @Autowired
    public PaymentMethodController(IPaymentMethodService service){
        this.service = service;
    }

    /**
     * {@code POST  /api/v1.0/configuration/payment-methods/save} : Create a new payment method and their payment plans.
     *
     * @param paymentMethodDto the {@link PaymentMethodDto} object to create.
     * @return the {@link PaymentMethodDto}.
     */
    @Operation(summary = "This endpoint is used to save new payment method along with payment plans")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "500", description = "Something went wrong"),
            @ApiResponse(responseCode = "400", description = "Validations failed"),
            @ApiResponse(responseCode = "4002", description = "Method name already exist")
    })
    @PostMapping (value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponseDto save(@Valid @RequestBody PaymentMethodDto paymentMethodDto){
        log.debug("REST request to save payment method : {}", paymentMethodDto);
        return service.save(paymentMethodDto);
    }

    /**
     * {@code GET  /api/v1.0/configuration/payment-methods} : get all the payment methods.
     * @return the {@link List<PaymentMethodDto>} with status {@code 200 (OK)} and the list of {@link PaymentMethodDto} in body.
     */
    @GetMapping
    @Operation(summary = "This endpoint is used to get all payment methods along with its payment plans based on given fields")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Something went wrong")
    })
    public PaymentResponseDto findAll(@RequestParam(value = "id",required = false) Integer paymentPlanId,
                                          @RequestParam(value = "name",required = false) String paymentMethodName){

        log.debug("REST request to get all payment method with paymentPlanId: {} and paymentMethodName : {}", paymentPlanId,paymentMethodName);
        PaymentResponseDto dto =  service.findAll(paymentPlanId,paymentMethodName);
        return dto;

    }

    /**
     * {@code POST  /api/v1.0/configuration/payment-methods} : Update a payment method and their payment plans.
     *
     * @param paymentMethodId the {@link Integer} object.
     * @param request the {@link PaymentMethodDto} object.
     * @return the {@link PaymentMethodEntity}.
     */
    @Operation(summary = "This endpoint is used to update the existing payment method and payment plans based on payment method id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated"),
            @ApiResponse(responseCode = "500", description = "Something went wrong"),
            @ApiResponse(responseCode = "400", description = "Validations failed"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PutMapping
    public PaymentResponseDto updatePaymentMethod(@RequestParam(value = "payment-methods",required = false) Integer paymentMethodId, @Valid @RequestBody PaymentMethodDto request) {
        log.debug("REST request to update payment method with paymentMethodId: {} and request : {}", paymentMethodId,request);
        return service.updatePaymentMethod(paymentMethodId, request);
    }
}
