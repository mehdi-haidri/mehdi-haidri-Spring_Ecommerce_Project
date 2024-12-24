package com.project.ecommerce.customer;


import lombok.*;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Validated
public class Address {

    String street;
    String houseNumber;
    String zipCode;

}
