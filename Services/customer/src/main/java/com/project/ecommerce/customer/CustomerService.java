package com.project.ecommerce.customer;

import com.project.ecommerce.exceptions.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final  CustomerRepository repository;
    private final CustomerMapper mapper;



    public String saveCustomer(CustomerRequest request) {

        try {
            var  customer = repository.save(mapper.toCustomer(request));
            return customer.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateCustomer(CustomerRequest request) {
        var customer =repository.findById(request.id()).orElseThrow(
                () -> new CustomerNotFoundException(format("Customer not found with this id  :  %s " ,request.id()))
        );

        mergeCustomer(customer ,request);
        repository.save(customer);

    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstName())) {
            customer.setFirstName(request.firstName());
        }
        if(StringUtils.isNotBlank(request.lastName())) {
            customer.setLastName(request.lastName());
        }
        if (StringUtils.isNotBlank(customer.getEmail())) {
            customer.setEmail(request.email());
        }
        if (request.address() != null){
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> getAllCustomers() {
       return repository.findAll().stream().map(mapper::fromCustomer).collect(Collectors.toList());
    }

    public Boolean checkCustomer(String customerId) {
            return repository.findById(customerId).isPresent();
    }

    public CustomerResponse getById(String customerId) {

        return   mapper.fromCustomer(repository.findById(customerId).orElseThrow(()-> new CustomerNotFoundException( format("No customer found with this Id : %s",customerId))));
    }

    public Void deleteCustomer(String customerId) {
        repository.deleteById(customerId);
        return null;
    }
}
