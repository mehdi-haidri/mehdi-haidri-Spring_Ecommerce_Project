package com.project.ecommerce.notification;

import com.project.ecommerce.customer.CustomerResponse;
import com.project.ecommerce.order.PaymentMethod;
import com.project.ecommerce.product.PurchasedProduct;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String OrderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchasedProduct> products
) {

}
