package com.project.ecommerce.order;


import java.math.BigDecimal;

public record OrderResponse(
        Integer id ,
        String reference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        String customerID
) {

}
