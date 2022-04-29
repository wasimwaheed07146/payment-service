package com.payment.enums.converters;

import com.payment.enums.Duration;

import javax.persistence.AttributeConverter;

/**
 * Attribute converter for {@link Duration} enum
 * @author Wasim Waheed.
 * @since 1.0
 */
public class DurationAttributeConverter implements AttributeConverter<Duration, Short> {
    @Override
    public Short convertToDatabaseColumn(Duration attribute) {
        if (attribute == null)
            return null;

        return attribute.value();
    }

    @Override
    public Duration convertToEntityAttribute(Short dbData) {
        if (dbData == null)
            return null;

        return Duration.valueOf(dbData);
    }
}
