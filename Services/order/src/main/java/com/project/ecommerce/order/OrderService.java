package com.project.ecommerce.order;


import com.project.ecommerce.customer.Customer;
import com.project.ecommerce.customer.CustomerClient;
import com.project.ecommerce.notification.OrderConfirmation;
import com.project.ecommerce.notification.OrderProducer;
import com.project.ecommerce.orderline.OrderLine;
import com.project.ecommerce.orderline.OrderLineService;
import com.project.ecommerce.payment.PaymentClient;
import com.project.ecommerce.product.ProductClient;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.project.ecommerce.order.OrderMapper.toOrderResponse;

@Service
@RequiredArgsConstructor
public class OrderService {

    final  private OrderRepository repository ;
    final  private OrderMapper mapper;
    final private CustomerClient customerClient;
    final private ProductClient productClient;
    final  private OrderLineService orderLineService;
    final private OrderProducer orderProducer;
    final private PaymentClient paymentClient;

    @Transactional(rollbackFor = EntityNotFoundException.class)
    public OrderResponse createOrder(OrderRequest request) {

        //Check customer
        var customer = customerClient.findCustomerById(request.customerId()).orElseThrow(
                () -> new EntityNotFoundException("Customer not found")
        );
        //purchase products

        var purshsedProducts = productClient.purchaseProducts(request.purchasedProductsList()).orElseThrow(
                () -> new EntityNotFoundException("Product not found")
        );

        //persist Order




        Order order = repository.save(mapper.toOrder(request));


        orderProducer.sendOrderConfirmation(
                    new OrderConfirmation(
                            request.reference(),
                            request.totalAmount(),
                            request.paymentMethod(),
                            customer,
                            request.purchasedProductsList()
                    )
            );

            paymentClient.createPayment(
                    new PaymentRequest(
                            order.getId(),
                            order.getReference(),
                            order.getTotalAmount(),
                            order.getPaymentMethod(),
                            customer
                    )
            );


        for( var purshsedProduct : purshsedProducts ) {
             orderLineService.createOrderLine(
                     OrderLine.builder()
                             .order(Order.builder().id(order.getId()).build())
                             .productId(purshsedProduct.productId())
                             .quantity(purshsedProduct.quantity()).build()
             );
        }
            return toOrderResponse(order);



    }

    public List<OrderResponse> findAll() {

        return repository.findAll().stream().map(OrderMapper::toOrderResponse).toList();
    }

    public OrderResponse findById(Integer orderId) {

        Order order =  repository.findById(orderId).orElseThrow(
                () -> new EntityNotFoundException("Order not found")
        );

        return toOrderResponse(order);
    }
}
