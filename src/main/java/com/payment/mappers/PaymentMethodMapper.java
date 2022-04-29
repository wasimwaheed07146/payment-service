package com.payment.mappers;

import com.payment.domain.PaymentMethodEntity;
import com.payment.domain.PaymentPlanEntity;
import com.payment.dto.PaymentMethodDto;
import com.payment.dto.PaymentPlanDto;
import org.mapstruct.*;

import java.util.List;

/**
 * @author Wasim Waheed.
 * @since 1.0
 */
@Mapper(componentModel = "spring")
public interface PaymentMethodMapper {

    PaymentMethodEntity mapToEntity(PaymentMethodDto paymentMethodDto);
    PaymentMethodDto mapToDto(PaymentMethodEntity paymentMethodEntity);
    List<PaymentMethodDto> mapToDto(List<PaymentMethodEntity> list);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    PaymentMethodEntity updatePaymentMethod(@MappingTarget PaymentMethodEntity existingPaymentMethodEntity, PaymentMethodDto source);

}
