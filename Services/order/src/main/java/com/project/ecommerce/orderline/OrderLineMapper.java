package com.project.ecommerce.orderline;


import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {


    public  static  OrderLineResponse toOrderLineResponse(OrderLine orderLine) {

        return  new OrderLineResponse(
                orderLine.getId(),
                orderLine.getProductId(),
                orderLine.getQuantity()
        );
    }
}
