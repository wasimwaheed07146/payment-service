package com.payment.dto;

import com.payment.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wasim Waheed.
 * @since 1.0
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PaymentMethodDto implements Serializable {
    private Integer id;
    @NotBlank(message = "name is mandatory")
    private String name;
    @NotBlank(message = "display name is mandatory")
    private String displayName;
    @NotNull(message = "paymentType must not be null")
    private PaymentType paymentType;
    @Valid
    private List<PaymentPlanDto> paymentPlans = new ArrayList<>();
}
