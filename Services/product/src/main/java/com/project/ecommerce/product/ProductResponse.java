package com.project.ecommerce.product;

import java.math.BigDecimal;
import java.util.List;

public record ProductResponse(
        Integer id,
        String name,
        String description,
        double quantity,
        BigDecimal price,
        Integer categoryId,
        String categoryName,
        String categoryDescription,
        List<String> imageUrl
) {


}
