package com.payment.dto;

import com.payment.enums.Currency;
import com.payment.enums.Duration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Wasim Waheed.
 * @since 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentPlanDto implements Serializable {
    private Integer id;
    @NotNull(message = "net amount is mandatory")
    private BigDecimal netAmount;
    private BigDecimal grossAmount;
    @NotNull(message = "currency must not be null")
    private Currency currency;
    @NotNull(message = "duration must not be null")
    private Duration duration;
    private BigDecimal taxAmount;


}
