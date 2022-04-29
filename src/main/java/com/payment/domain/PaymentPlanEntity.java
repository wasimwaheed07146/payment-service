package com.payment.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.payment.enums.Currency;
import com.payment.enums.Duration;
import com.payment.enums.converters.CurrencyAttributeConverter;
import com.payment.enums.converters.DurationAttributeConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * This entity contains Payment Plans.
 * Many Payment plans can be associated with one Payment method.
 *
 * @author Wasim Waheed.
 * @since 1.0
 */
@Entity(name = "payment_plan")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentPlanEntity implements Persistable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "net_amount")
    private BigDecimal netAmount;

    @Column(name = "gross_amount")
    private BigDecimal grossAmount;

    @Column(name = "currency")
    @Convert(converter = CurrencyAttributeConverter.class)
    private Currency currency;

    @Column(name = "duration")
    @Convert(converter = DurationAttributeConverter.class)
    private Duration duration;

    @Column(name = "tax_amount")
    private BigDecimal taxAmount;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="payment_method_id",nullable = false)
    private PaymentMethodEntity paymentMethodEntity;

}
