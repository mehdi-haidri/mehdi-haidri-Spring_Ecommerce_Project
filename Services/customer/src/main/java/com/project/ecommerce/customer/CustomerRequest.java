package com.project.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;



public record CustomerRequest(

    String id,
    @NotNull(message = "the first name is required")
    String firstName,
    @NotNull(message = "the last name is required")
    String lastName,
    @NotNull(message = "the email  is required")
    @Email(message = "this is not a valid email ")
    String email,
    Address address
){

}
