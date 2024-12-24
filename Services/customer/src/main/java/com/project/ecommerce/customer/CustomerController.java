package com.project.ecommerce.customer;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final  CustomerService service;

    @PostMapping
    public ResponseEntity<String> createCustomer(
            @RequestBody @Valid CustomerRequest request
    ) {
        return ResponseEntity.ok(service.saveCustomer(request));
    }
    @PutMapping
    public ResponseEntity<String> updateCustomer(
            @RequestBody @Valid CustomerRequest request
    ){
        service.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public  ResponseEntity<List<CustomerResponse>> getAllCustomers(){
        return  ResponseEntity.ok(service.getAllCustomers());
    }

    @GetMapping("/exist/{customer-id}")
    public  ResponseEntity<Boolean> checkIfCustomerExists(
            @PathVariable("customer-id") String customerId
    ){
        return ResponseEntity.ok(service.checkCustomer(customerId));
    }
    @GetMapping("/{customer-id}")
    public  ResponseEntity<CustomerResponse> getCustomer(
            @PathVariable("customer-id") String customerId
    ){
        return ResponseEntity.ok(service.getById(customerId));
    }
    @DeleteMapping("/{customer-id}")
    public  ResponseEntity<Void> deleteCustomer(
            @PathVariable("customer-id") String customerId
    ){
        service.deleteCustomer(customerId);
        return  ResponseEntity.accepted().build();
    }
}
