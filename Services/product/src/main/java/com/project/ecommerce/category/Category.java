package com.project.ecommerce.category;

import com.project.ecommerce.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Entity
public class Category {
    @Id
    @GeneratedValue
    Integer id;
    String name;
    String description;
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    List<Product> products;
}
