package com.project.ecommerce.order;

import com.project.ecommerce.customer.Customer;
import com.project.ecommerce.customer.CustomerResponse;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer orderId,
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        CustomerResponse customer
) {
}
