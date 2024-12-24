package com.project.ecommerce.orderline;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orderLines")
@RequiredArgsConstructor
public class OrderLineController {

    private final OrderLineService service;


    @GetMapping("/{order-id}")
    public ResponseEntity<List<OrderLineResponse>> getOrderLines(
            @RequestParam("order-id") Integer orderId
    ) {

        return ResponseEntity.ok(service.findByOrderId(orderId));

    }


}
