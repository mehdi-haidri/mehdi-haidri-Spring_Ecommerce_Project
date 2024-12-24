package com.project.ecommerce.orderline;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    final private OrderLineRepository repository;
    final private OrderLineMapper mapper;

    public  void createOrderLine(OrderLine orderLine) {
          repository.save(orderLine);
    }


    public List<OrderLineResponse> findByOrderId(Integer orderId) {

        return  repository.findByOrderId(orderId).stream().map(OrderLineMapper::toOrderLineResponse).toList();
    }
}
