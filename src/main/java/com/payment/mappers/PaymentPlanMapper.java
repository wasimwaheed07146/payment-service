package com.payment.mappers;

import com.payment.domain.PaymentMethodEntity;
import com.payment.domain.PaymentPlanEntity;
import com.payment.dto.PaymentMethodDto;
import com.payment.dto.PaymentPlanDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentPlanMapper {
    PaymentPlanEntity mapToEntity(PaymentPlanDto paymentPlanDto);
}
