package com.project.ecommerce.payment;


import com.project.ecommerce.notification.NotificationProducer;
import com.project.ecommerce.notification.PaymentConfirmation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class PaymentService {
    private  final  PaymentRepository repository;
    private final NotificationProducer notificationProducer;
    private final PaymentMapper mapper;


    public Integer createPayment(PaymentRequest request) {

        Payment payment  = repository.save(mapper.toPayment(request));

        notificationProducer.sendPaymentConfirmation(
                new PaymentConfirmation(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstname(),
                        request.customer().lastname(),
                        request.customer().email()
                )
        );

        return payment.getId();

    }
}
