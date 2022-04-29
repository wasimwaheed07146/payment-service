package com.payment.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wasim Waheed.
 * @since 1.0
 */
public enum Currency {

    USD((short)1, "US dollar"),
    SAR((short)2, "Saudi Riyal"),
    AED((short)3, "Emirates Dirham"),
    PKR((short)4, "Pakistani rupee");

    private final Short value;
    private final String description;

    private static final Map<Short, Currency> valueMap = new HashMap<>();
    private static final Map<String, Currency> descriptionMap = new HashMap<>();

    static {
        Arrays.stream(Currency.values()).forEach(k -> valueMap.put(k.value, k));
        Arrays.stream(Currency.values()).forEach(k -> descriptionMap.put(k.description, k));
    }

    Currency (Short value,String description){
        this.value = value;
        this.description = description;
    }

    public static Currency valueOf(Short value) {
        return valueMap.get(value);
    }
    public static Currency descriptionOfCurrency(String description){return descriptionMap.get(description);}

    public Short value(){return this.value;}

    public String description(){return this.description;}


}
