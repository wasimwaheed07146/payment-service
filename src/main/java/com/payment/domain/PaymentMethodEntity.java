package com.payment.domain;

import com.payment.enums.PaymentType;
import com.payment.enums.converters.PaymentTypeAttributeConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This entity contains Payment Methods.
 * One Payment method can have multiple Payment plans
 *
 * @author Wasim Waheed.
 * @since 1.0
 */
@Entity(name = "payment_method")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodEntity implements Persistable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "display_name")
    private String displayName;

    @Convert(converter = PaymentTypeAttributeConverter.class)
    private PaymentType paymentType;

    @OneToMany(mappedBy = "paymentMethodEntity",
               fetch = FetchType.LAZY,
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private List<PaymentPlanEntity> paymentPlans = new ArrayList<>();

}
