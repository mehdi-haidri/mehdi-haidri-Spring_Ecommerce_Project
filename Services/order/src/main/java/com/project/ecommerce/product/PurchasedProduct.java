package com.project.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record PurchasedProduct (

        @NotNull(message = "the product id is required")
        Integer productId,
        @NotNull(message = "the product Quantity  is required")
        double quantity
){


}
