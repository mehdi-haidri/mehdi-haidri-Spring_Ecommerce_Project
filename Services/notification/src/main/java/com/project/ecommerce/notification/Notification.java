package com.project.ecommerce.notification;

import com.project.ecommerce.kafka.order.OrderConfirmation;
import com.project.ecommerce.kafka.payment.PaymentConfirmation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document
@Builder

public class Notification {

    @Id
    String id;
    NotificationType notificationType;
    LocalDateTime timestamp;
    OrderConfirmation orderConfirmation;
    PaymentConfirmation paymentConfirmation;
}
