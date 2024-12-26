package com.project.ecommerce.product;

import java.math.BigDecimal;
import java.util.List;

public record ProductPurchaseResponse(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
