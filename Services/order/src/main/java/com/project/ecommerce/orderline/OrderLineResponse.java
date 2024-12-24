package com.project.ecommerce.orderline;

public record OrderLineResponse(
        Integer Id,
        Integer productId,
        Double quantity
) {

}
