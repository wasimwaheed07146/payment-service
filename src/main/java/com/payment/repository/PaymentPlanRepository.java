package com.payment.repository;

import com.payment.domain.PaymentPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Wasim Waheed.
 * @since 1.0
 */
@Repository
public interface PaymentPlanRepository extends JpaRepository<PaymentPlanEntity,Integer> {
}
