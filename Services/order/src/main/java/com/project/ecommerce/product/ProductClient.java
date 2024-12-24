package com.project.ecommerce.product;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@FeignClient(
        name = "product-service",
        url = "${application.config.product-url}"
)
public interface ProductClient {

    @PostMapping("/purchase")
    Optional<List<PurchaseResponse>> purchaseProducts( List <PurchasedProduct> purchasedProducts);

}
