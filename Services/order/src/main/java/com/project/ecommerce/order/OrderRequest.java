package com.project.ecommerce.order;
import com.project.ecommerce.product.PurchasedProduct;


import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(

        PaymentMethod paymentMethod,
        BigDecimal totalAmount ,
        String customerId,
        String reference,
        List<PurchasedProduct> purchasedProductsList
) {
}
