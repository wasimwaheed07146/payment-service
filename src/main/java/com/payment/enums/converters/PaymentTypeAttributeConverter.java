package com.payment.enums.converters;

import com.payment.enums.PaymentType;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;

/**
 * Attribute converter for {@link PaymentType} enum
 * @author Wasim Waheed.
 * @since 1.0
 */
@Component
public class PaymentTypeAttributeConverter implements AttributeConverter<PaymentType, Short> {
    @Override
    public Short convertToDatabaseColumn(PaymentType attribute) {
        if (attribute == null)
            return null;

        return attribute.value();
    }

    @Override
    public PaymentType convertToEntityAttribute(Short dbData) {
        if (dbData == null)
            return null;

        return PaymentType.valueOf(dbData);
    }
}
