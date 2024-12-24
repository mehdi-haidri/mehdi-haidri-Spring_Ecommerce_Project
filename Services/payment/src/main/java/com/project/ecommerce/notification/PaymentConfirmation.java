package com.project.ecommerce.notification;


import com.project.ecommerce.payment.PaymentMethod;

import java.math.BigDecimal;

public record PaymentConfirmation (
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstname,
        String customerLastname,
        String customerEmail
){


}
