package com.project.ecommerce.payment;


import com.project.ecommerce.order.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(
        name = "payment-service" ,
        url = "${application.config.payment-url}"
)
public interface PaymentClient {

    @PostMapping
    Optional<Integer> createPayment(@RequestBody PaymentRequest paymentRequest);

}
