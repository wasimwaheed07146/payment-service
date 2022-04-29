package com.payment.repository;

import com.payment.domain.PaymentMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author Wasim Waheed.
 * @since 1.0
 */
@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethodEntity,Integer>, JpaSpecificationExecutor<PaymentMethodEntity> {

    boolean existsByName(String name);
}
