package com.payment.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/**
 * @author Wasim Waheed.
 * @since 1.0
 */
public enum PaymentType {

    CREDIT_CARD((short)1, "Credit cards"),
    MOBILE_CARRIER((short)2, "Mobile carrier"),
    VOUCHER((short)3, "Voucher");

    private static final Map<Short, PaymentType> valueMap = new HashMap<>();
    private static final Map<String, PaymentType> descriptionMap = new HashMap<>();


    static {
        Arrays.stream(PaymentType.values()).forEach(k -> valueMap.put(k.value, k));
    }

    static {
        Arrays.stream(PaymentType.values()).forEach(k -> descriptionMap.put(k.description, k));
    }

    PaymentType(Short value, String description) {
        this.value = value;
        this.description = description;
    }

    private final Short value;
    private final String description;

    public static PaymentType valueOf(Short value) {
        return valueMap.get(value);
    }
    public static PaymentType descriptionOfDuration(String description){return descriptionMap.get(description);}

    public Short value() {
        return this.value;
    }

    public String description(){return this.description; }
}
