package com.project.ecommerce.payment;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer orderId,
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Customer customer
) {
}
