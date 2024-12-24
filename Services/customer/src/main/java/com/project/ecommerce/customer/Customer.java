package com.project.ecommerce.customer;


import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Customer {

    @Id
    String id;
    String firstName;
    String lastName;
    String email;
    Address address;
}
