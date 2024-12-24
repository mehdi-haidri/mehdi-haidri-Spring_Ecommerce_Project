package com.project.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;


public record Customer(
        String id,
        String firstname,
        String lastname,
        String email

){}


