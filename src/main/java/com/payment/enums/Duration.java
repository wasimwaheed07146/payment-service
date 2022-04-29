package com.payment.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/**
 * @author Wasim Waheed.
 * @since 1.0
 */
public enum Duration {

    MONTH((short)1, "Monthly"),
    WEEK((short)2, "Weekly"),
    YEAR((short)3, "Yearly");

    private static final Map<Short, Duration> valueMap = new HashMap<>();
    private static final Map<String, Duration> descriptionMap = new HashMap<>();


    static {
        Arrays.stream(Duration.values()).forEach(k -> valueMap.put(k.value, k));
    }

    static {
        Arrays.stream(Duration.values()).forEach(k -> descriptionMap.put(k.description, k));
    }
    private final Short value;
    private final String description;

    Duration (Short value,String description){
        this.value = value;
        this.description = description;
    }

    public static Duration valueOf(Short value) {
        return valueMap.get(value);
    }
    public static Duration descriptionOfDuration(String description){return descriptionMap.get(description);}

    public short value(){return this.value;}

    public String description(){return this.description;}
}
