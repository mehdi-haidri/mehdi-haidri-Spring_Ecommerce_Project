package com.project.ecommerce.product;

import com.project.ecommerce.category.Category;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Entity
public class Product {
    @Id
    @GeneratedValue
    Integer id;
    String name;
    BigDecimal price;
    Double quantity;
    String description;
    String image;
    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;
}
