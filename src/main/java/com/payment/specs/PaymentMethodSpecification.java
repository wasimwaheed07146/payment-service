package com.payment.specs;

import com.payment.domain.PaymentMethodEntity;
import com.payment.dto.PaymentFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Wasim Waheed.
 * @since 1.0
 */
public class PaymentMethodSpecification implements Specification<PaymentMethodEntity> {

    private final PaymentFilter paymentFilter;

    public PaymentMethodSpecification(PaymentFilter paymentFilter) {
        this.paymentFilter = paymentFilter;
    }

    @Override
    public Predicate toPredicate(Root<PaymentMethodEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (paymentFilter != null) {
            if (paymentFilter.getPaymentMethodName() != null) {
                predicates.add(criteriaBuilder.like(root.get("name"), paymentFilter.getPaymentMethodName()));
            }
            if (paymentFilter.getPaymentPlanId() != null) {
                Join<Object, Object> paymentMethodEntityPaymentPlanEntityJoin  =(Join<Object, Object>) root.fetch("paymentPlans");
                predicates.add(criteriaBuilder.equal(paymentMethodEntityPaymentPlanEntityJoin.get("id"), paymentFilter.getPaymentPlanId()));
            }
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
