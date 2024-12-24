package com.project.ecommerce.kafka;

import com.project.ecommerce.email.EmailService;
import com.project.ecommerce.kafka.order.OrderConfirmation;
import com.project.ecommerce.kafka.payment.PaymentConfirmation;
import com.project.ecommerce.notification.Notification;
import com.project.ecommerce.notification.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.project.ecommerce.notification.NotificationType.ORDER_CONFIRMATION;
import static com.project.ecommerce.notification.NotificationType.PAYMENT_CONFIRMATION;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaNotificationConsumer {


    private  final NotificationRepository repository;
    private  final EmailService emailService;


    @KafkaListener( topics = "payment-topic")
    public void  ConsumePaymentConfirmation(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("Payment confirmation: {}", paymentConfirmation);

        repository.save(Notification.builder()
                .notificationType(PAYMENT_CONFIRMATION)
                .paymentConfirmation(paymentConfirmation)
                .build()
        );

        var customerName = paymentConfirmation.customerFirstname() + " " + paymentConfirmation.customerLastname();

        emailService.sendPaymentConfirmationEmail(
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );

    }

    @KafkaListener( topics = "order-topic")
    public void  ConsumeOrderConfirmation(OrderConfirmation orderConfirmation ) throws MessagingException {
        log.info("order confirmation: {}", orderConfirmation);

        repository.save(Notification.builder()
                .notificationType(ORDER_CONFIRMATION)
                .orderConfirmation(orderConfirmation)
                .build()
        );
            var customerName = orderConfirmation.customer().firstName() + " " + orderConfirmation.customer().lastName();

            emailService.sendOrderConfirmationEmail(
                    orderConfirmation.customer().email(),
                    customerName,
                    orderConfirmation.totalAmount(),
                    orderConfirmation.OrderReference(),
                    orderConfirmation.products()
            );


    }
}
