package com.project.ecommerce.order;


import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public Order toOrder(OrderRequest request) {
        return Order.builder()
                .customerId(request.customerId())
                .paymentMethod(request.paymentMethod())
                .totalAmount(request.totalAmount())
                .reference(request.reference())
                .build();
    }

    public static OrderResponse toOrderResponse(Order order) {

        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}
