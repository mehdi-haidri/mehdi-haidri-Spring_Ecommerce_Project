package com.project.ecommerce.order;


import com.project.ecommerce.orderline.OrderLine;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table( name = "customer_order")
public class Order {
    @Id
    @GeneratedValue
    Integer id;
    @Column(unique=true , nullable=false)
    String reference;
    @Column( nullable=false)
    BigDecimal totalAmount ;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @OneToMany(mappedBy = "order")
    List<OrderLine> orderlines ;
    @Column( nullable=false)
    String customerId;
    @CreatedDate
    @Column(nullable=false , updatable=false)
    LocalDateTime created_at ;
    @LastModifiedDate
    @Column(nullable =  true , insertable = false)
    LocalDateTime updated_at ;

}
