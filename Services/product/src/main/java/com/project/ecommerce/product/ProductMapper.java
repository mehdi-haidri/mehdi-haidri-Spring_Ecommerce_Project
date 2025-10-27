package com.project.ecommerce.product;


/*import com.project.ecommerce.aws.S3Service;*/
import com.project.ecommerce.category.Category;
import jdk.jfr.Registered;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class ProductMapper {




    public Product toProduct(ProductRequest request) {
        String Image_Path = "";
       /* if (!request.image().isEmpty()){
           Image_Path = "Bucket/" +request.image().getName();
        }*/

        return Product.builder()
                .image(Image_Path)
                .name(request.name())
                .quantity(request.quantity())
                .price(request.price())
                .description(request.description())
                .category(Category.builder().id(request.categoryId()).build()).build();
    }
    public Product toProduct(ProductPurchaseRequest request) {
        return Product.builder()
                .quantity(request.quantity())
                .id(request.productId()).build();
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product storedProduct , double quantity ) {

        return new ProductPurchaseResponse(
                storedProduct.getId(),
                storedProduct.getName(),
                storedProduct.getDescription(),
                storedProduct.getPrice(),
                quantity
        );

    }

    public static ProductResponse toProductResponse(Product product ,List <String> imageUrls) {



        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription(),
                imageUrls
                        );
    }
}
