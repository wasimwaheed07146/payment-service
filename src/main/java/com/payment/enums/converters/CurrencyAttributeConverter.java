package com.payment.enums.converters;

import com.payment.enums.Currency;

import javax.persistence.AttributeConverter;
/**
 * Attribute converter for {@link Currency} enum
 * @author Wasim Waheed.
 * @since 1.0
 */
public class CurrencyAttributeConverter implements AttributeConverter<Currency, Short> {

    @Override
    public Short convertToDatabaseColumn(Currency attribute) {
        if (attribute == null)
            return null;

        return attribute.value();
    }

    @Override
    public Currency convertToEntityAttribute(Short dbData) {
        if (dbData == null)
            return null;

        return Currency.valueOf(dbData);
    }
}
