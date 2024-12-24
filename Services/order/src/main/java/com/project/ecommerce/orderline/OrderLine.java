package com.project.ecommerce.orderline;


import com.project.ecommerce.order.Order;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table( name = "order_line")
public class OrderLine {

    @Id
    @GeneratedValue
    Integer id;

    @Column(nullable = false)
    Integer productId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order ;
    Double quantity;


}
